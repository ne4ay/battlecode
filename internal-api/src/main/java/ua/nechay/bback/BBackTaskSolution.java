package ua.nechay.bback;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.api.AllegedTaskSolution;
import ua.nechay.bback.api.ProgrammingLanguage;
import ua.nechay.bback.api.TestCase;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * @author anechaev
 * @since 30.04.2022
 */
public class BBackTaskSolution implements AllegedTaskSolution {

    @JsonProperty private final String programText;
    @JsonProperty private final ProgrammingLanguage programmingLanguage;
    @JsonProperty private final Collection<TestCase> testCases;

    public BBackTaskSolution(String programText, @Nonnull ProgrammingLanguage programmingLanguage, @Nonnull Collection<TestCase> testCases) {
        this.programText = programText;
        this.programmingLanguage = programmingLanguage;
        this.testCases = testCases;
    }

    @Override
    public String getProgramText() {
        return programText;
    }

    @Override
    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public Collection<TestCase> getTestCases() {
        return testCases;
    }
}
