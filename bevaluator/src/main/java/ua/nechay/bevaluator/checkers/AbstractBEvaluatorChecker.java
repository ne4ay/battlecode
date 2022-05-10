package ua.nechay.bevaluator.checkers;

import org.apache.commons.io.FileUtils;
import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackCheckedTestCase;
import ua.nechay.bback.BBackTaskSolution;
import ua.nechay.bback.BBackTestCase;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author anechaev
 * @since 10.05.2022
 */
public abstract class AbstractBEvaluatorChecker implements TaskEvaluator {
    protected static final String INPUT_FILE_NAME = "input.txt";
    protected static final String OUTPUT_FILE_NAME = "output.txt";
    protected static final long SINGLE_CASE_TIMEOUT_SEC = 5L;
    protected static final long ALL_CASE_TIMEOUT_SEC = 12L;
    protected static final int COUNT_OF_THREADS_FOR_TASK_CHECK = 3;
    protected static final List<Character> TERMINAL_CHARACTERS = Arrays.asList('\r', '\n');

    @Nonnull
    abstract BEvaluatorType getEvaluatorType();

    protected String getLanguageOrThrow(@Nonnull BBackTaskSolution taskSolution) {
        String language = taskSolution.getProgrammingLanguage();
        if (!getEvaluatorType().getLanguageName().equals(language)) {
            throw new IllegalStateException("Incompatible programming language: " + language);
        }
        return language;
    }

    protected void createAndWriteInputFile(@Nonnull String basePath, @Nonnull BBackTestCase testCase) throws IOException {
        Path inputFilePath = Path.of(basePath, INPUT_FILE_NAME);
        try (OutputStream outputStream = Files.newOutputStream(inputFilePath)) {
            outputStream.write(testCase.getInputCase().getBytes(StandardCharsets.UTF_8));
        }
    }

    protected Path createOutputFile(@Nonnull String basePath) throws IOException {
        Path outputFilePath = Path.of(basePath, OUTPUT_FILE_NAME);
        Files.createFile(outputFilePath);
        return outputFilePath;
    }

    protected void createExecutableFile(@Nonnull String basePath, @Nonnull String fileName, @Nonnull String programText)
        throws IOException
    {
        Path filePath = Path.of(basePath, fileName);
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            outputStream.write(programText.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * @param consoleHandler consumer, that handle console
     * @return Errors
     * @throws IOException
     * @throws InterruptedException
     */
    protected String tryToWorkInSh(Consumer<PrintWriter> consoleHandler) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("sh");
        AtomicReference<String> errors = new AtomicReference<>("");
        InputStreamGobbler streamGobbler =
            new InputStreamGobbler(process.getErrorStream(), err -> errors.set(errors.get() + "\n" + err));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(streamGobbler);
        PrintWriter stdin = new PrintWriter(process.getOutputStream());
        consoleHandler.accept(stdin);

        stdin.close();
        process.waitFor(SINGLE_CASE_TIMEOUT_SEC, TimeUnit.SECONDS);
        process.destroy();
        executorService.shutdown();
        return errors.get();
    }

    protected void deleteDirectory(@Nonnull Path dirPath) {
        try {
            FileUtils.deleteDirectory(dirPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BBackCheckedTestCase tryToCheckTestCase(@Nonnull String baseDirPath, @Nonnull BBackTestCase testCase,
        @Nonnull Supplier<BBackCheckedTestCase> testCaseChecker)
    {
        Path dirPath = Path.of(baseDirPath);
        try {
            Files.createDirectories(dirPath);
        } catch (Exception e) {
            return BBackCheckedTestCase.fromException(testCase, e.toString());
        }
        try {
            return testCaseChecker.get();
        } finally {
          deleteDirectory(dirPath);
        }
    }

    protected static String adjustOutput(@Nonnull String output) {
        String adjustedOutput = output;
        for (int i = 0; i < 2; i++) {
            if (isLastCharacterTerminal(adjustedOutput)) {
                adjustedOutput = cutOffLastCharacter(adjustedOutput);
            }
        }
        return adjustedOutput;
    }

    private static boolean isLastCharacterTerminal(@Nonnull String line) {
        return line.length() > 0 && TERMINAL_CHARACTERS.contains(line.charAt(line.length() - 1));
    }

    private static String cutOffLastCharacter(@Nonnull String line) {
        return line.length() > 0 ? line.substring(0, line.length() - 1) : line;
    }

    protected BBackCheckedTestCase handleOutput(@Nonnull BBackTestCase testCase, @Nonnull String output, @Nonnull String error) {
        String adjustedOutput = adjustOutput(output);
        if (error.length() != 0) {
            return BBackCheckedTestCase.fromException(testCase, error);
        }
        return BBackCheckedTestCase.fromOutput(testCase, adjustedOutput);
    }

    protected BBackCheckedSolution handleTestCaseTasks(@Nonnull List<Callable<BBackCheckedTestCase>> callables) {
        ExecutorService executor = Executors.newFixedThreadPool(COUNT_OF_THREADS_FOR_TASK_CHECK);
        List<BBackCheckedTestCase> checkedTestCases;
        try {
            checkedTestCases = executor.invokeAll(callables, ALL_CASE_TIMEOUT_SEC, TimeUnit.SECONDS)
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
}
