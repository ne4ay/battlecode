package ua.nechay.bback.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.TaskTO;
import ua.nechay.bback.dto.TestCaseTO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class AddNewTaskRequest {
    @JsonProperty private final TaskTO task;

    public AddNewTaskRequest(@Nonnull TaskTO task) {
       this.task = task;
    }

    public String getTitle() {
        return task.getTitle();
    }

    public String getDescription() {
        return task.getDescription();
    }

    public int getCost() {
        return task.getCost();
    }

    public List<String> getLanguages() {
        return task.getLanguages();
    }

    public List<TestCaseTO> getTestCases() {
        return task.getTestCases();
    }
}
