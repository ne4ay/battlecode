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
 * @since 10.05.2022
 */
public class BEvaluatorPythonChecker extends AbstractBEvaluatorChecker implements TaskEvaluator {


    @Override
    public BBackCheckedSolution check(@Nonnull BBackTaskSolution taskSolution) {
        getLanguageOrThrow(taskSolution);
        String programText = taskSolution.getProgramText();
        if (programText == null) {
            return new BBackCheckedSolution(false, EvaluatingException.PROGRAM_CODE_IS_NULL.name(), Collections.emptyList());
        }
        List<Callable<BBackCheckedTestCase>> solutionCallables = taskSolution.getTestCases()
            .stream()
            .map(testCase -> getCaseCallable(testCase, programText))
            .toList();
        return handleTestCaseTasks(solutionCallables);
    }

    private Callable<BBackCheckedTestCase> getCaseCallable(@Nonnull BBackTestCase testCase, @Nonnull String programText) {
        return () -> testSingleCase(testCase, programText);
    }

    private BBackCheckedTestCase testSingleCase(@Nonnull BBackTestCase testCase, @Nonnull String programText) {
        String uuidString = UUID.randomUUID()
            .toString()
            .replace("-", "");
        return tryToCheckTestCase(uuidString, testCase, () -> {
            try {
                return checkTestCase(uuidString, programText, testCase);
            } catch (Exception e) {
                e.printStackTrace();
                return BBackCheckedTestCase.fromException(testCase, e.toString());
            }
        });
    }

    private BBackCheckedTestCase checkTestCase(@Nonnull String baseDirPath, @Nonnull String programText, @Nonnull BBackTestCase testCase)
        throws IOException, InterruptedException
    {
        String executableFileName = "main.py";
        Path outputFilePath = createOutputFile(baseDirPath);
        createAndWriteInputFile(baseDirPath, testCase);
        createExecutableFile(baseDirPath, executableFileName, programText);
        String error = tryToWorkInSh(stdin -> {
            stdin.println("cd " + baseDirPath);
            stdin.println("python " + executableFileName + " < " + INPUT_FILE_NAME + " > " + OUTPUT_FILE_NAME);
        });
        String output = Files.readString(outputFilePath);
        return handleOutput(testCase, output, error);
    }

    @Override
    @Nonnull
    BEvaluatorType getEvaluatorType() {
        return BEvaluatorType.PYTHON;
    }
}
