package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author anechaev
 * @since 30.04.2022
 */
public class BBackTaskSolution {

    @JsonProperty private final String programText;
    @JsonProperty private final List<BBackTestCase> testCases;

    public BBackTaskSolution(String programText, List<BBackTestCase> testCases) {
        this.programText = programText;
        this.testCases = testCases;
    }

    public String getProgramText() {
        return programText;
    }

    public List<BBackTestCase> getTestCases() {
        return testCases;
    }
}
