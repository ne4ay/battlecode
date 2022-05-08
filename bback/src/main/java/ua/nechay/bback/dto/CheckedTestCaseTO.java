package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.CheckedTestCase;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 07.05.2022
 */
public class CheckedTestCaseTO implements CheckedTestCase {

    @JsonProperty private final String inputCase;
    @JsonProperty private final String expectedOutput;
    @JsonProperty private final String actualOutput;
    @JsonProperty private final boolean isCorrect;

    public CheckedTestCaseTO(String inputCase, String expectedOutput, String actualOutput, boolean isCorrect) {
        this.inputCase = inputCase;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
        this.isCorrect = isCorrect;
    }

    public static CheckedTestCaseTO fromTestCase(@Nonnull CheckedTestCase checkedTestCase) {
        return new CheckedTestCaseTO(
            checkedTestCase.getInputCase(),
            checkedTestCase.getExpectedOutput(),
            checkedTestCase.getActualOutput(),
            checkedTestCase.isCorrect());
    }

    @Override
    public String getInputCase() {
        return inputCase;
    }

    @Override
    public String getExpectedOutput() {
        return expectedOutput;
    }

    @Override
    public String getActualOutput() {
        return actualOutput;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
