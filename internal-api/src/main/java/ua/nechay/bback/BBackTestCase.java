package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.TestCase;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BBackTestCase implements TestCase {

    @JsonProperty private final String inputCase;
    @JsonProperty private final String expectedOutput;

    public BBackTestCase(@Nonnull String inputCase, @Nonnull String expectedOutput) {
        this.inputCase = inputCase;
        this.expectedOutput = expectedOutput;
    }

    public BBackTestCase(@Nonnull TestCase testCase) {
        this.inputCase = testCase.getInputCase();
        this.expectedOutput = testCase.getExpectedOutput();
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
    public String toString() {
        return "BBackTestCase{" +
            "inputCase='" + inputCase + '\'' +
            ", expectedOutput='" + expectedOutput + '\'' +
            '}';
    }
}
