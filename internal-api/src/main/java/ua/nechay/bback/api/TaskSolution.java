package ua.nechay.bback.api;

import java.util.Collection;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public interface TaskSolution {

    Collection<? extends TestCase> getTestCases();
}
