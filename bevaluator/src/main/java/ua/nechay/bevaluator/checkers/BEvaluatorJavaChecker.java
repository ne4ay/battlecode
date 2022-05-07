package ua.nechay.bevaluator.checkers;

import org.apache.commons.io.FileUtils;
import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackCheckedTestCase;
import ua.nechay.bback.BBackTaskSolution;
import ua.nechay.bback.BBackTestCase;
import ua.nechay.bback.EvaluatingException;
import ua.nechay.bevaluator.BEvaluatorType;
import ua.nechay.bevaluator.InputStreamGobbler;
import ua.nechay.bevaluator.TaskEvaluator;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BEvaluatorJavaChecker implements TaskEvaluator {
    private static final String CLASS_NAME = "Main";
    private static final String PUBLIC_CLASS_NAME = "public class " + CLASS_NAME;
    private static final String PUBLIC_METHOD_MAIN = "public static void main(String[] args) {";
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String OUTPUT_FILE_NAME = "output.txt";
    private static final long SINGLE_CASE_TIMEOUT_SEC = 5L;
    private static final long ALL_CASE_TIMEOUT_SEC = 12L;

    public BBackCheckedSolution check(@Nonnull BBackTaskSolution taskSolution) {
        String language = taskSolution.getProgrammingLanguage();
        if (!BEvaluatorType.JAVA.getLanguageName().equals(language)) {
            throw new IllegalStateException("Incompatible programming language: " + language);
        }
        String trimmedProgramText = taskSolution.getProgramText().trim();
        int countOfPublicClassEntries = countOfEntries(trimmedProgramText, PUBLIC_CLASS_NAME);
        if (countOfPublicClassEntries == 0) {
            return new BBackCheckedSolution(false, EvaluatingException.DONT_CONTAIN_PUBLIC_MAIN_CLASS.name(), Collections.emptyList());
        }
        if (countOfPublicClassEntries > 1) {
            return new BBackCheckedSolution(false, EvaluatingException.TOO_MANY_PUBLIC_MAIN_CLASS.name(), Collections.emptyList());
        }
        int countOfMethodEntries = countOfEntries(trimmedProgramText, PUBLIC_METHOD_MAIN);
        if (countOfMethodEntries != 1) {
            return new BBackCheckedSolution(false, EvaluatingException.TOO_MANY_PUBLIC_MAIN_CLASS.name(), Collections.emptyList());
        }
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<BBackCheckedTestCase>> solutionCallables = taskSolution.getTestCases()
            .stream()
            .map(testCase -> getCaseCallable(testCase, trimmedProgramText))
            .toList();
        List<BBackCheckedTestCase> checkedTestCases;
        try {
            checkedTestCases = executor.invokeAll(solutionCallables, ALL_CASE_TIMEOUT_SEC, TimeUnit.SECONDS)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        return new BBackCheckedTestCase("", "", e.toString(), false);
                    } catch (ExecutionException e) {
                        return new BBackCheckedTestCase("", "", e.getCause().toString(), false);
                    }
                })
                .toList();
        } catch (InterruptedException e) {
            return new BBackCheckedSolution(false, e.toString(), Collections.emptyList());
        } finally {
            executor.shutdown();
        }
        boolean isSuccessful = checkedTestCases.stream()
            .allMatch(BBackCheckedTestCase::isCorrect);
        return new BBackCheckedSolution(isSuccessful, null, checkedTestCases);
    }

    private static int countOfEntries(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }


    private Callable<BBackCheckedTestCase> getCaseCallable(@Nonnull BBackTestCase testCase, @Nonnull String trimmedProgramText) {
        return () -> testSingleCase(testCase, trimmedProgramText);
    }

    private BBackCheckedTestCase testSingleCase(@Nonnull BBackTestCase testCase, @Nonnull String trimmedProgramText) {
        String uuidString = UUID.randomUUID()
            .toString()
            .replace("-", "");
        String className = CLASS_NAME + uuidString;
        String processedProgramText = trimmedProgramText.replace(PUBLIC_CLASS_NAME, PUBLIC_CLASS_NAME + uuidString);
        Path dirPath = Path.of(uuidString);
        try {
            Files.createDirectories(dirPath);
        } catch (Exception e) {
            return BBackCheckedTestCase.fromException(testCase, e.toString());
        }
        try {
            Path inputFilePath = Path.of(uuidString, INPUT_FILE_NAME);
            try (OutputStream outputStream = Files.newOutputStream(inputFilePath)) {
                outputStream.write(testCase.getInputCase().getBytes(StandardCharsets.UTF_8));
            }
            Path outputFilePath = Path.of(uuidString, OUTPUT_FILE_NAME);
            Files.createFile(outputFilePath);
            String fileName = className + ".java";
            Path filePath = Path.of(uuidString, fileName);
            try (OutputStream outputStream = Files.newOutputStream(filePath)) {
                outputStream.write(processedProgramText.getBytes(StandardCharsets.UTF_8));
            }
            Process process = Runtime.getRuntime()
                .exec("sh");

            AtomicReference<String> errors = new AtomicReference<>("");
            InputStreamGobbler streamGobbler =
                new InputStreamGobbler(process.getErrorStream(), err -> errors.set(errors.get() + err));
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(streamGobbler);

            PrintWriter stdin = new PrintWriter(process.getOutputStream());
            stdin.println("cd " + uuidString);
            stdin.println("javac " + fileName);
            stdin.println("java -classpath . " + className + " < " + INPUT_FILE_NAME + " > " + OUTPUT_FILE_NAME);
            stdin.close();
            process.waitFor(SINGLE_CASE_TIMEOUT_SEC, TimeUnit.SECONDS);
            process.destroy();
            executorService.shutdown();
            String output = Files.readString(Path.of(uuidString, OUTPUT_FILE_NAME));
            String errorString = errors.get();
            if (errorString.length() != 0) {
                BBackCheckedTestCase.fromException(testCase, errorString);
            }
            return BBackCheckedTestCase.fromOutput(testCase, output);
        } catch (Exception e) {
            return BBackCheckedTestCase.fromException(testCase, e.toString());
        } finally {
            try {
                FileUtils.deleteDirectory(dirPath.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
