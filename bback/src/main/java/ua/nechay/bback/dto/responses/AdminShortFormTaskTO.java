package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.CategoryModel;
import ua.nechay.bback.domain.LanguageToTaskModel;
import ua.nechay.bback.domain.TaskModel;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 02.05.2022
 */
public class AdminShortFormTaskTO {

    @JsonProperty private final long taskId;
    @JsonProperty private final int cost;
    @JsonProperty private final String title;
    @JsonProperty private final String complexity;
    @JsonProperty private final List<String> categories;
    @JsonProperty private final List<String> languages;

    public AdminShortFormTaskTO(long taskId, int cost, String title, String complexity,
        List<String> categories, List<String> languages)
    {
        this.taskId = taskId;
        this.cost = cost;
        this.title = title;
        this.complexity = complexity;
        this.categories = categories;
        this.languages = languages;
    }

    public AdminShortFormTaskTO(@Nonnull TaskModel task) {
        this.taskId = task.getId();
        this.cost = task.getCost();
        this.title = task.getTitle();
        this.complexity = task.getComplexity().getName();
        this.categories = task.getCategories().stream()
            .map(CategoryModel::getName)
            .collect(Collectors.toList());
        this.languages = task.getLanguages().stream()
            .map(LanguageToTaskModel::getLanguage)
            .map(BBackLanguage::getLanguageName)
            .collect(Collectors.toList());
    }
}
