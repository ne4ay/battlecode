package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.CheckedTestCase;
import ua.nechay.bback.api.TestCase;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BBackCheckedTestCase implements CheckedTestCase {

    @JsonProperty private final String inputCase;
    @JsonProperty private final String expectedOutput;
    @JsonProperty private final String actualOutput;
    @JsonProperty private final boolean isCorrect;

    public BBackCheckedTestCase(@Nonnull String expectedInput, @Nonnull String expectedOutput, @Nonnull String actualOutput, boolean isCorrect) {
        this.inputCase = expectedInput;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
        this.isCorrect = isCorrect;
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

    @Override
    public boolean isCorrect() {
        return isCorrect;
    }

    public static BBackCheckedTestCase fromException(@Nonnull TestCase testCase, @Nonnull String exception) {
        return new BBackCheckedTestCase(testCase.getInputCase(), testCase.getExpectedOutput(), exception, false);
    }

    public static BBackCheckedTestCase fromOutput(@Nonnull TestCase testCase, @Nonnull String output) {
        boolean isSuccessful = testCase.getExpectedOutput().equals(output);
        return new BBackCheckedTestCase(testCase.getInputCase(), testCase.getExpectedOutput(), output, isSuccessful);
    }
}
