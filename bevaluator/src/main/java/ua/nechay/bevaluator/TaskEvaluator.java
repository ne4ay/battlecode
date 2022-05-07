package ua.nechay.bevaluator;

import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackTaskSolution;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public interface TaskEvaluator {

    BBackCheckedSolution check(@Nonnull BBackTaskSolution taskSolution);
}
