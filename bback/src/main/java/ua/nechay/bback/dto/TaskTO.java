package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.LanguageToTaskModel;
import ua.nechay.bback.domain.TaskModel;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 05.05.2022
 */
public class TaskTO {

    @JsonProperty private final String title;
    @JsonProperty private final int cost;
    @JsonProperty private final String description;
    @JsonProperty private final List<String> languages;
    @JsonProperty private final List<TestCaseTO> testCases;

    public TaskTO(String title, int cost, String description, List<String> languages, List<TestCaseTO> testCases) {
        this.title = title;
        this.cost = cost;
        this.description = description;
        this.languages = languages;
        this.testCases = testCases;
    }

    public TaskTO(@Nonnull TaskModel task) {
        this.title = task.getTitle();
        this.cost = task.getCost();
        this.description = task.getDescription();
        this.languages = task.getLanguages().stream()
            .map(LanguageToTaskModel::getLanguage)
            .map(BBackLanguage::getLanguageName)
            .collect(Collectors.toList());
        this.testCases = task.getTestCases().stream()
            .map(testCase -> new TestCaseTO(testCase.getInputCase(), testCase.getExpectedOutput()))
            .collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<TestCaseTO> getTestCases() {
        return testCases;
    }
}
