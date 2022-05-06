package ua.nechay.bevaluator.checkers;

import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackTaskSolution;
import ua.nechay.bback.EvaluatingException;
import ua.nechay.bback.api.ProgrammingLanguage;
import ua.nechay.bevaluator.BEvaluatorType;
import ua.nechay.bevaluator.TaskEvaluator;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BEvaluatorJavaChecker implements TaskEvaluator {
    private static final String PUBLIC_CLASS_MAIN = "public class Main";


    public BBackCheckedSolution check(@Nonnull BBackTaskSolution taskSolution) {
        ProgrammingLanguage language = taskSolution.getProgrammingLanguage();
        if (!language.getLanguageName().equals(BEvaluatorType.JAVA.getLanguageName())) {
            throw new IllegalStateException("Incompatible programming language: " + language);
        }
        String trimmedProgramText = taskSolution.getProgramText().trim();
        if (!trimmedProgramText.contains(PUBLIC_CLASS_MAIN)) {
            return new BBackCheckedSolution(false, EvaluatingException.DONT_CONTAIN_PUBLIC_MAIN_CLASS.name(), Collections.emptyList());
        }

    }
}
