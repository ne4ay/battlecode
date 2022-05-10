package ua.nechay.bback.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.BBackTaskStatus;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.domain.user.UserRoleModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.AddNewTaskRequest;
import ua.nechay.bback.dto.requests.ChangeUserRequest;
import ua.nechay.bback.dto.responses.AdminAllTasksResponse;
import ua.nechay.bback.dto.responses.AdminAllUsersResponse;
import ua.nechay.bback.dto.responses.AdminRolesResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.PageCountResponse;
import ua.nechay.bback.dto.responses.SimpleCheckingResponse;
import ua.nechay.bback.service.TaskService;
import ua.nechay.bback.service.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.nechay.bback.dto.responses.GeneralResponseException.NOT_FOUND;
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
    private final UserService userService;

    public AdminController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping(value = "/tasks/add")
    public @ResponseBody
    GenericResponse<SimpleCheckingResponse, GeneralResponseException>
    postNewTask(@RequestBody AddNewTaskRequest request)
    {
        List<String> langNames = request.getLanguages();
        if (langNames.size() == 0) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        List<BBackLanguage> languages = BBackLanguage.fromNamesCollection(langNames);
        if (langNames.size() != languages.size()) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        TaskModel task = fillBuilderFromRequest(request, languages).build();
        taskService.save(task);
        return SimpleCheckingResponse.createGenericResponse(true);
    }

    @GetMapping(value = "/tasks/show", params = {"page", "size"})
    public @ResponseBody
    GenericResponse<AdminAllTasksResponse, GeneralResponseException>
    getAllTasks(@RequestParam("page") int page, @RequestParam("size") int size)
    {
        if (page <= 0 || size <= 0) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        long countOfAllTasks = taskService.getCountOfAllTasks();
        int countOfPages = (int) (countOfAllTasks / size + 1);
        if (page - 1 > countOfPages) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        List<TaskModel> tasks = taskService.getAllTasks(page - 1, size);
        return AdminAllTasksResponse.createGenericResponse(tasks, countOfPages, size);
    }

    @Deprecated
    @GetMapping(value = "/tasks/count", params = {"size"})
    public @ResponseBody
    GenericResponse<PageCountResponse, GeneralResponseException>
    getPageCountOfAllTasks(@RequestParam("size") int size)
    {
        if (size <= 0) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        long countOfAllTasks = taskService.getCountOfAllTasks();
        long countOfPages = countOfAllTasks / size + 1;
        return PageCountResponse.createGenericResponse((int) countOfPages, size);
    }

    @Transactional
    @DeleteMapping(value = "/tasks/delete/{id}")
    public @ResponseBody
    GenericResponse<SimpleCheckingResponse, GeneralResponseException> deleteTask(@PathVariable long id) {
        taskService.delete(id);
        return SimpleCheckingResponse.createGenericResponse(true);
    }

    @Transactional
    @PutMapping(value = "/tasks/update/{id}")
    public @ResponseBody GenericResponse<SimpleCheckingResponse, GeneralResponseException>
    updateTask(@PathVariable long id, @RequestBody AddNewTaskRequest request)
    {
        List<String> langNames = request.getLanguages();
        if (langNames.size() == 0) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        List<BBackLanguage> languages = BBackLanguage.fromNamesCollection(langNames);
        if (langNames.size() != languages.size()) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        TaskModel task = fillBuilderFromRequest(request, languages)
            .setId(id)
            .build();

        taskService.update(task);
        return SimpleCheckingResponse.createGenericResponse(true);
    }

    @GetMapping(value = "/users/show", params = {"page", "size"})
    public @ResponseBody
    GenericResponse<AdminAllUsersResponse, GeneralResponseException>
    getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size)
    {
        if (size <= 0) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        long countOfAllUsers = userService.getCountOfAllUsers();
        int countOfPages = (int) (countOfAllUsers / size + 1);
        if (page - 1 > countOfPages) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        List<UserModel> users = userService.getAllUsers(page - 1, size);
        return AdminAllUsersResponse.createGenericResponse(users, countOfPages, size);
    }

    @GetMapping(value = "/users/roles")
    public @ResponseBody GenericResponse<AdminRolesResponse, GeneralResponseException> getAllRoles() {
        return AdminRolesResponse.createGenericResponse(Arrays.asList(BBackUserRole.values()));
    }

    @Transactional
    @PutMapping(value = "/users/update/{id}")
    public @ResponseBody GenericResponse<SimpleCheckingResponse, GeneralResponseException>
    updateUser(@PathVariable long id, @RequestBody ChangeUserRequest request) {
        List<String> roleNames = request.getRoles();
        if (roleNames.isEmpty()) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        List<BBackUserRole> roles = roleNames.stream()
            .map(BBackUserRole::fromNameId)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        if (roleNames.size() != roles.size()) {
            return GenericResponse.fromException(WRONG_REQUEST);
        }
        Optional<UserModel> maybeUser = userService.findById(id);
        if (maybeUser.isEmpty()) {
            return GenericResponse.fromException(NOT_FOUND);
        }
        UserModel user = maybeUser.get();
        Set<UserRoleModel> roleModels = roles.stream()
            .map(role -> new UserRoleModel(role, user))
            .collect(Collectors.toSet());
        user.setRoles(roleModels);
        userService.update(user);
        return SimpleCheckingResponse.createGenericResponse(true);
    }

    private TaskModel.Builder fillBuilderFromRequest(AddNewTaskRequest request, List<BBackLanguage> languages) {
        return new TaskModel.Builder()
            .setTitle(request.getTitle())
            .setDescription(request.getDescription())
            .setCost(request.getCost())
            .setStatus(BBackTaskStatus.APPROVED)
            .setLanguages(languages)
            .setTestCases(request.getTestCases());
    }
}
