package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.CheckedTaskSolution;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author anechaev
 * @since 01.05.2022
 */
public class BBackCheckedSolution implements CheckedTaskSolution {

    @JsonProperty private boolean isSuccessful;
    @JsonProperty private String exception;
    @JsonProperty private Collection<BBackCheckedTestCase> testCases;

    public BBackCheckedSolution(boolean isSuccessful, @Nullable String exception, @Nonnull Collection<BBackCheckedTestCase> testCases) {
        this.isSuccessful = isSuccessful;
        this.testCases = testCases;
        this.exception = exception;
    }

    public BBackCheckedSolution() {
    }

    @Override
    public boolean isSuccessful() {
        return isSuccessful;
    }

    @Nullable
    @Override
    public String getException() {
        return exception;
    }

    @Override
    public Collection<BBackCheckedTestCase> getTestCases() {
        return testCases;
    }

    @Override
    public String toString() {
        return "BBackCheckedSolution{" +
            "isSuccessful=" + isSuccessful +
            ", exception='" + exception + '\'' +
            ", testCases=" + testCases +
            '}';
    }
}
