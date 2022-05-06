package ua.nechay.bback.api;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public interface CheckedTaskSolution extends TaskSolution {

    boolean isSuccessful();

    @Nullable
    String getException();

    @Override
    Collection<? extends CheckedTestCase> getTestCases();
}
