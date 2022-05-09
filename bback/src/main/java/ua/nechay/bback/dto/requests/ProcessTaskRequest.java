package ua.nechay.bback.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class ProcessTaskRequest {

    @JsonProperty private final long taskId;
    @JsonProperty private final String programText;
    @JsonProperty private final String language;

    public ProcessTaskRequest(long taskId, String programText, String language) {
        this.taskId = taskId;
        this.programText = programText;
        this.language = language;
    }

    public long getTaskId() {
        return taskId;
    }

    public String getProgramText() {
        return programText;
    }

    public String getLanguage() {
        return language;
    }
}
