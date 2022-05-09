package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.api.CheckedTaskSolution;
import ua.nechay.bback.dto.CheckedTestCaseTO;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 07.05.2022
 */
public class ProcessTaskResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;
    @JsonProperty private final String exception;
    @JsonProperty private final int countOfExperience;
    @JsonProperty private final List<CheckedTestCaseTO> testCases;

    public ProcessTaskResponse(boolean isSuccessful, String exception, int countOfExperience,
        List<CheckedTestCaseTO> testCases)
    {
        this.isSuccessful = isSuccessful;
        this.exception = exception;
        this.countOfExperience = countOfExperience;
        this.testCases = testCases;
    }

    @Nonnull
    public static ProcessTaskResponse fromSolution(@Nonnull CheckedTaskSolution solution, int countOfExperience) {
        List<CheckedTestCaseTO> checkedTestCases = solution.getTestCases()
            .stream()
            .filter(testCase -> solution.isSuccessful() || !testCase.isCorrect())
            .limit(1)
            .map(CheckedTestCaseTO::fromTestCase)
            .toList();
        return new ProcessTaskResponse(
            solution.isSuccessful(),
            solution.getException(),
            countOfExperience,
            checkedTestCases
        );
    }
}
