package ua.nechay.bback.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class ProcessTaskRequest {

    @JsonProperty private final long taskId;
    @JsonProperty private final String textOfProgram;
    @JsonProperty private final String language;

    public ProcessTaskRequest(long taskId, String textOfProgram, String language) {
        this.taskId = taskId;
        this.textOfProgram = textOfProgram;
        this.language = language;
    }

    public long getTaskId() {
        return taskId;
    }

    public String getTextOfProgram() {
        return textOfProgram;
    }

    public String getLanguage() {
        return language;
    }
}
