package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class TestCaseTO {

    @JsonProperty private final String inputCase;
    @JsonProperty private final String expectedOutput;

    public TestCaseTO(String inputCase, String expectedOutput) {
        this.inputCase = inputCase;
        this.expectedOutput = expectedOutput;
    }
}
