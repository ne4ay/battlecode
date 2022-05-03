package ua.nechay.bback.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.repo.LanguageToTaskRepo;
import ua.nechay.bback.repo.TaskRepo;

import java.util.List;

/**
 * @author anechaev
 * @since 01.05.2022
 */
@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final LanguageToTaskRepo languageToTaskRepo;

    public TaskService(TaskRepo taskRepo, LanguageToTaskRepo languageToTaskRepo) {
        this.taskRepo = taskRepo;
        this.languageToTaskRepo = languageToTaskRepo;
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
        int offset = page * size;
        return taskRepo.findAllByLanguage(language.name(), offset, size);
    }

    public List<TaskModel> getAllTasks() {
        return taskRepo.findAll();
    }

    public List<TaskModel> getAllTasks(int page, int size) {
        return taskRepo.findAll(PageRequest.of(page, size)).toList();
    }

    public long getCountOfAllTasks() {
        return taskRepo.count();
    }
}
