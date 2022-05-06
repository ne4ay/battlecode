package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.TestCase;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class TestCaseTO implements TestCase {

    @JsonProperty private final String inputCase;
    @JsonProperty private final String expectedOutput;

    public TestCaseTO(String inputCase, String expectedOutput) {
        this.inputCase = inputCase;
        this.expectedOutput = expectedOutput;
    }

    @Override
    public String getInputCase() {
        return inputCase;
    }

    @Override
    public String getExpectedOutput() {
        return expectedOutput;
    }
}
