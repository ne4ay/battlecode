package ua.nechay.bback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.BBackTaskStatus;
import ua.nechay.bback.domain.TaskCompletionModel;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.PageCountResponse;
import ua.nechay.bback.dto.responses.ParticularLanguageRelatedTasksResponse;
import ua.nechay.bback.dto.responses.ShortFormTaskTO;
import ua.nechay.bback.service.TaskCompletionService;
import ua.nechay.bback.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.nechay.bback.utils.BBackAuthUtils.getUserFromAuthentication;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired private final TaskService taskService;
    @Autowired private final TaskCompletionService taskCompletionService;

    public TaskController(TaskService taskService, TaskCompletionService taskCompletionService) {
        this.taskService = taskService;
        this.taskCompletionService = taskCompletionService;
    }

    @GetMapping(value = "/count/{lang}", params = {"size"})
    public @ResponseBody
    GenericResponse<PageCountResponse, GeneralResponseException> getPageCountOnRelatedLang(
        @PathVariable String lang,
        @RequestParam("size") int size)
    {
        Optional<BBackLanguage> maybeLang = BBackLanguage.fromName(lang);
        if (maybeLang.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        long countOfAllTasks = taskService.getCountOfTasksRelatedToStatus(BBackTaskStatus.APPROVED);
        long countOfPages = countOfAllTasks / size + 1;
        return PageCountResponse.createGenericResponse((int) countOfPages, size);
    }

    @GetMapping(value = "/{lang}", params = {"page", "size"})
    public @ResponseBody
    GenericResponse<ParticularLanguageRelatedTasksResponse, GeneralResponseException>
    getAllTasksRelatedToTheLang(@PathVariable String lang, @RequestParam("page") int page, @RequestParam("size") int size)
    {
        if (page <= 0) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        Optional<BBackLanguage> maybeLang = BBackLanguage.fromName(lang);
        if (maybeLang.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        BBackLanguage language = maybeLang.get();
        Optional<UserModel> maybeUser = getUserFromAuthentication();
        if (maybeUser.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.UNAUTHORIZED);
        }
        UserModel user = maybeUser.get();
        List<TaskModel> langRelatedTasks =
            taskService.getAllTasksRelatedToLanguageAndStatus(language, BBackTaskStatus.APPROVED, page - 1, size);
        Set<TaskModel> completedTasks =
            taskCompletionService.getAllTaskCompletionAccordingToLanguageAndUser(language, user, langRelatedTasks)
                .stream()
                .map(TaskCompletionModel::getTask)
                .collect(Collectors.toSet());
        List<ShortFormTaskTO> taskTOs = langRelatedTasks.stream()
            .map(task -> new ShortFormTaskTO(task, completedTasks::contains))
            .toList();
        return ParticularLanguageRelatedTasksResponse.createGenericResponse(taskTOs);
    }
}
