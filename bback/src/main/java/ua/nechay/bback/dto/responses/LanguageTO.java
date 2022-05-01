package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anechaev
 * @since 01.05.2022
 */
public class LanguageTO {

    @JsonProperty private final String name;
    @JsonProperty private final int countOfTasks;

    public LanguageTO(String name, int countOfTasks) {
        this.name = name;
        this.countOfTasks = countOfTasks;
    }
}
