package ua.nechay.bback.checkers;

import ua.nechay.bback.api.AllegedTaskSolution;
import ua.nechay.bback.api.CheckedTaskSolution;

import java.util.Optional;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public interface TaskChecker {

    Optional<CheckedTaskSolution> check(AllegedTaskSolution allegedTaskSolution);
}
