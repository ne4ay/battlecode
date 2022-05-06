package ua.nechay.bevaluator;

import ua.nechay.bback.api.ProgrammingLanguage;

public enum BEvaluatorType implements ProgrammingLanguage {
    JAVA("Java");

    private final String name;

    BEvaluatorType(String name) {
        this.name = name;
    }

    @Override
    public String getLanguageName() {
        return name;
    }
}
