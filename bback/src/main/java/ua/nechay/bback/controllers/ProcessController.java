package ua.nechay.bback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.BBackTaskSolution;
import ua.nechay.bback.BBackTestCase;
import ua.nechay.bback.api.CheckedTaskSolution;
import ua.nechay.bback.api.TestCase;
import ua.nechay.bback.checkers.TaskChecker;
import ua.nechay.bback.config.BBackProperties;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.TaskCompletionModel;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.domain.TestCaseModel;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.ProcessTaskRequest;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.ProcessTaskResponse;
import ua.nechay.bback.service.TaskCompletionService;
import ua.nechay.bback.service.TaskService;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static ua.nechay.bback.utils.BBackAuthUtils.getUserFromAuthentication;

/**
 * @author anechaev
 * @since 06.05.2022
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired private final TaskService taskService;
    @Autowired private final TaskCompletionService taskCompletionService;
    @Autowired private final UserService userService;
    @Autowired private final TaskChecker taskChecker;
    @Autowired private final BBackProperties properties;

    public ProcessController(TaskService taskService, TaskCompletionService taskCompletionService, UserService userService,
        TaskChecker taskChecker, BBackProperties properties)
    {
        this.taskService = taskService;
        this.taskCompletionService = taskCompletionService;
        this.userService = userService;
        this.taskChecker = taskChecker;
        this.properties = properties;
    }

    @PostMapping
    public @ResponseBody GenericResponse<ProcessTaskResponse, GeneralResponseException>
    processTask(@RequestBody ProcessTaskRequest request) {
        long taskId = request.getTaskId();
        Optional<TaskModel> maybeTask = taskService.getTaskById(taskId);
        if (maybeTask.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.NOT_FOUND);
        }
        TaskModel task = maybeTask.get();
        Set<TestCaseModel> testCases = task.getTestCases();
        Optional<BBackLanguage> maybeLanguage = BBackLanguage.fromName(request.getLanguage());
        if (maybeLanguage.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.NOT_FOUND);
        }
        BBackLanguage language = maybeLanguage.get();
        Optional<UserModel> maybeUser = getUserFromAuthentication();
        if (maybeUser.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.UNAUTHORIZED);
        }
        UserModel user = maybeUser.get();
        BBackTaskSolution taskSolution = createSolution(request.getProgramText(), language, testCases);

        CompletableFuture<Optional<CheckedTaskSolution>> futureSolution = taskChecker.check(taskSolution);
        List<TaskCompletionModel> completions = taskCompletionService.getAllByLanguageAndUserAndTaskId(language, user, taskId);
        Optional<CheckedTaskSolution> maybeSolution;
        try {
            maybeSolution = futureSolution.get(properties.getCheckingTimeoutMs(), TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException e) {
            return GenericResponse.fromException(GeneralResponseException.SERVER_ERROR);
        } catch (TimeoutException e) {
            return GenericResponse.fromException(GeneralResponseException.TIMEOUT);
        }
        if (maybeSolution.isEmpty()) {
            return GenericResponse.fromException(GeneralResponseException.NOT_FOUND);
        }
        CheckedTaskSolution checkedSolution = maybeSolution.get();
        if (completions.size() > 1) {
            throw new IllegalStateException("There are more than one completion of task: " + taskId);
        }
        if (!completions.isEmpty()) { // if user has done this task before
            return createResponse(checkedSolution, 0);
        }
        if (!checkedSolution.isSuccessful()) {
            return createResponse(checkedSolution, 0);
        }
        TaskCompletionModel taskCompletionModel = new TaskCompletionModel.Builder()
            .setTask(task)
            .setUser(user)
            .setLanguage(language)
            .setTimeOfCompletion(ZonedDateTime.now(ZoneId.of("UTC")))
            .build();
        taskCompletionService.save(taskCompletionModel);
        user.increaseExperience(task.getCost());
        userService.save(user);
        return createResponse(checkedSolution, task.getCost());
    }

    private static BBackTaskSolution createSolution(@Nonnull String programText, @Nonnull BBackLanguage language,
        Collection<? extends TestCase> testCases) {
        List<BBackTestCase> testCaseTos = testCases.stream()
            .map(BBackTestCase::new)
            .toList();
        return new BBackTaskSolution(programText, language.getLanguageName(), testCaseTos);
    }

    private static GenericResponse<ProcessTaskResponse, GeneralResponseException>
    createResponse(@Nonnull CheckedTaskSolution checkedSolution, int countOfExperience)
    {
        return GenericResponse.fromResponse(ProcessTaskResponse.fromSolution(checkedSolution, countOfExperience));
    }
}