package ua.nechay.bback.service;

import org.springframework.stereotype.Service;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.repo.LanguageToTaskRepo;
import ua.nechay.bback.repo.TaskRepo;

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

}
