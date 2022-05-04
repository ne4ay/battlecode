package ua.nechay.bback.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.TestCaseTO;

import java.util.List;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class AddNewTaskRequest {
    @JsonProperty private final String title;
    @JsonProperty private final String description;
    @JsonProperty private final int cost;
    @JsonProperty private final List<String> languages;
    @JsonProperty private final List<TestCaseTO> testCases;

    public AddNewTaskRequest(String title, String description, int cost, List<String> languages, List<TestCaseTO> testCases) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.languages = languages;
        this.testCases = testCases;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<TestCaseTO> getTestCases() {
        return testCases;
    }
}
