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
    @JsonProperty private final String programmingLanguage;
    @JsonProperty private final Collection<BBackTestCase> testCases;

    public BBackTaskSolution(String programText, @Nonnull String programmingLanguage, @Nonnull Collection<BBackTestCase> testCases) {
        this.programText = programText;
        this.programmingLanguage = programmingLanguage;
        this.testCases = testCases;
    }

    @Override
    public String getProgramText() {
        return programText;
    }

    @Override
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public Collection<BBackTestCase> getTestCases() {
        return testCases;
    }
}
