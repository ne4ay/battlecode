package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.CheckedTaskSolution;
import ua.nechay.bback.api.CheckedTestCase;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author anechaev
 * @since 01.05.2022
 */
public class BBackCheckedSolution implements CheckedTaskSolution {

    @JsonProperty private final boolean isSuccessful;
    @JsonProperty private final String exception;
    @JsonProperty private final Collection<CheckedTestCase> testCases;

    public BBackCheckedSolution(boolean isSuccessful, @Nonnull String exception, @Nonnull Collection<CheckedTestCase> testCases) {
        this.isSuccessful = isSuccessful;
        this.testCases = testCases;
        this.exception = exception;
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
    public Collection<CheckedTestCase> getTestCases() {
        return testCases;
    }
}
