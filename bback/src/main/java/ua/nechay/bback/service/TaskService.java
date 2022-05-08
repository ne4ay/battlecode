package ua.nechay.bback.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.BBackTaskStatus;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.domain.TestCaseModel;
import ua.nechay.bback.repo.LanguageToTaskRepo;
import ua.nechay.bback.repo.TaskRepo;
import ua.nechay.bback.repo.TestCaseRepo;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

import static ua.nechay.bback.service.BBackServiceUtils.getOffsetForRepo;

/**
 * @author anechaev
 * @since 01.05.2022
 */
@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final LanguageToTaskRepo languageToTaskRepo;
    private final TestCaseRepo testCaseRepo;

    public TaskService(TaskRepo taskRepo, LanguageToTaskRepo languageToTaskRepo, TestCaseRepo testCaseRepo) {
        this.taskRepo = taskRepo;
        this.languageToTaskRepo = languageToTaskRepo;
        this.testCaseRepo = testCaseRepo;
    }

    public int getCountOfTheTasksOnLanguage(BBackLanguage language) {
        return languageToTaskRepo.countLanguageToTaskModelByLanguage(language);
    }

    public List<TaskModel> getAllTasksRelatedToLanguage(BBackLanguage language) {
        return taskRepo.findAllByLanguage(language.name());
    }

    /**
     * @param language programming language
     * @param page from 0
     * @param size size of the page
     * @return tasks
     */
    public List<TaskModel> getAllTasksRelatedToLanguage(BBackLanguage language, int page, int size) {
        int offset = getOffsetForRepo(page, size);
        return taskRepo.findAllByLanguage(language.name(), offset, size);
    }

    public List<TaskModel> getAllTasksRelatedToLanguageAndStatus(BBackLanguage language, BBackTaskStatus status, int page, int size) {
        int offset = getOffsetForRepo(page, size);
        return taskRepo.findAllByLanguageAndStatus(language.name(), status.name(), offset, size);
    }

    @Nonnull
    public List<TaskModel> getAllTasks() {
        return taskRepo.findAll();
    }

    @Nonnull
    public List<TaskModel> getAllTasks(int page, int size) {
        return taskRepo.findAll(PageRequest.of(page, size)).toList();
    }

    public Optional<TaskModel> getTaskById(long id) {
        return taskRepo.findById(id);
    }

    public List<TestCaseModel> getAllTestCasesByTaskId(long id) {
        return testCaseRepo.findAllByTaskId(id);
    }

    public long getCountOfAllTasks() {
        return taskRepo.count();
    }

    public long getCountOfTasksRelatedToStatus(@Nonnull BBackTaskStatus status) {
        return taskRepo.countAllByStatusIs(status);
    }

    public void save(@Nonnull TaskModel taskModel) {
        taskRepo.save(taskModel);
        languageToTaskRepo.saveAll(taskModel.getLanguages());
        testCaseRepo.saveAll(taskModel.getTestCases());
    }

    public void delete(long id) {
        languageToTaskRepo.deleteAllByTaskId(id);
        testCaseRepo.deleteAllByTaskId(id);
        taskRepo.deleteById(id);
    }
}
