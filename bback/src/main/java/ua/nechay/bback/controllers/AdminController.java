package ua.nechay.bback.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.BBackTaskStatus;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.AddNewTaskRequest;
import ua.nechay.bback.dto.responses.AddNewTaskResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.service.TaskService;

import java.util.List;

import static ua.nechay.bback.dto.responses.GeneralResponseException.WRONG_REQUEST;

/**
 * @author anechaev
 * @since 02.05.2022
 */
@RestController
@RequestMapping(AdminController.ADMIN_PATH)
public class AdminController {
    public final static String ADMIN_PATH = "/admin";

    private final TaskService taskService;

    public AdminController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/task/add")
    public @ResponseBody GenericResponse<AddNewTaskResponse, GeneralResponseException>
    postNewTask(@RequestBody AddNewTaskRequest request) {
        System.out.println(request); //TODO: clean
        List<String> langNames = request.getLanguages();
        if (langNames.size() == 0) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        List<BBackLanguage> languages = BBackLanguage.fromNamesCollection(langNames);
        if (langNames.size() != languages.size()) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        TaskModel task = new TaskModel.Builder()
            .setTitle(request.getTitle())
            .setDescription(request.getDescription())
            .setCost(request.getCost())
            .setStatus(BBackTaskStatus.APPROVED)
            .setLanguages(languages)
            .build();
        taskService.save(task);
        return AddNewTaskResponse.createGenericResponse(true);
    }
}
