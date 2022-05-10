package ua.nechay.bevaluator.checkers;

import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackCheckedTestCase;
import ua.nechay.bback.BBackTaskSolution;
import ua.nechay.bback.BBackTestCase;
import ua.nechay.bback.EvaluatingException;
import ua.nechay.bevaluator.BEvaluatorType;
import ua.nechay.bevaluator.TaskEvaluator;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BEvaluatorJavaChecker extends AbstractBEvaluatorChecker implements TaskEvaluator {
    private static final String CLASS_NAME = "Main";
    private static final String PUBLIC_CLASS_NAME = "public class " + CLASS_NAME;
    private static final String PUBLIC_METHOD_MAIN = "public static void main(String[] args) {";

    public BBackCheckedSolution check(@Nonnull BBackTaskSolution taskSolution) {
        getLanguageOrThrow(taskSolution);
        String programText = taskSolution.getProgramText();
        if (programText == null) {
            return new BBackCheckedSolution(false, EvaluatingException.PROGRAM_CODE_IS_NULL.name(), Collections.emptyList());
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
        List<Callable<BBackCheckedTestCase>> solutionCallables = taskSolution.getTestCases()
            .stream()
            .map(testCase -> getCaseCallable(testCase, trimmedProgramText))
            .toList();
        return handleTestCaseTasks(solutionCallables);
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
        return tryToCheckTestCase(uuidString, testCase, () -> {
            try {
                return checkTestCase(uuidString, className, processedProgramText, testCase);
            } catch (Exception e) {
                e.printStackTrace();
                return BBackCheckedTestCase.fromException(testCase, e.toString());
            }
        });
    }

    private BBackCheckedTestCase checkTestCase(@Nonnull String baseDirPath, @Nonnull String className,
        @Nonnull String programText, @Nonnull BBackTestCase testCase) throws IOException, InterruptedException
    {
        String executableFileName = className + ".java";
        Path outputFilePath = createOutputFile(baseDirPath);
        createAndWriteInputFile(baseDirPath, testCase);
        createExecutableFile(baseDirPath, executableFileName, programText);
        String error = tryToWorkInSh(stdin -> {
            stdin.println("cd " + baseDirPath);
            stdin.println("javac " + executableFileName);
            stdin.println("java -classpath . " + className + " < " + INPUT_FILE_NAME + " > " + OUTPUT_FILE_NAME);
        });
        String output = Files.readString(outputFilePath);
        return handleOutput(testCase, output, error);
    }

    @Override
    @Nonnull
    BEvaluatorType getEvaluatorType() {
        return BEvaluatorType.JAVA;
    }
}
