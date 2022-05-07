package ua.nechay.bevaluator;

import ua.nechay.bback.api.ProgrammingLanguage;
import ua.nechay.bevaluator.checkers.BEvaluatorJavaChecker;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

public enum BEvaluatorType implements ProgrammingLanguage {
    JAVA("Java", new BEvaluatorJavaChecker());

    private final String name;
    private final TaskEvaluator taskEvaluator;

    BEvaluatorType(String name, TaskEvaluator evaluator) {
        this.name = name;
        this.taskEvaluator = evaluator;
    }

    public static Optional<BEvaluatorType> fromProgrammingLanguageName(@Nonnull String language) {
        return Arrays.stream(BEvaluatorType.values())
            .filter(langName -> langName.getLanguageName().equals(language))
            .findFirst();
    }

    @Override
    public String getLanguageName() {
        return name;
    }

    public TaskEvaluator getTaskEvaluator() {
        return taskEvaluator;
    }
}
