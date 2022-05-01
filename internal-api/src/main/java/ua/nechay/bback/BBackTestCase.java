package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 30.04.2022
 */
public class BBackTestCase {

    @JsonProperty private final String expectedInput;
    @JsonProperty private final String expectedOutput;
    @JsonProperty private final String actualOutput;

    public BBackTestCase(@Nonnull String expectedInput, @Nonnull String expectedOutput, @Nonnull String actualOutput) {
        this.expectedInput = expectedInput;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
    }

    public String getExpectedInput() {
        return expectedInput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public String getActualOutput() {
        return actualOutput;
    }
}
