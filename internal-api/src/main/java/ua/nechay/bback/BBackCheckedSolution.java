package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author anechaev
 * @since 01.05.2022
 */
public class BBackCheckedSolution {

    @JsonProperty private final boolean isSuccessful;
    @JsonProperty private final String programText;
    @JsonProperty private final List<BBackTestCase> testCases;

    public BBackCheckedSolution(boolean isSuccessful, String programText, List<BBackTestCase> testCases) {
        this.isSuccessful = isSuccessful;
        this.programText = programText;
        this.testCases = testCases;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getProgramText() {
        return programText;
    }

    public List<BBackTestCase> getTestCases() {
        return testCases;
    }
}
