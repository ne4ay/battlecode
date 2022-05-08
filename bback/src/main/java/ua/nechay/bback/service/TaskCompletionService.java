package ua.nechay.bback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.TaskCompletionModel;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.repo.TaskCompletionRepo;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 02.05.2022
 */
@Service
public class TaskCompletionService {

    @Autowired private final TaskCompletionRepo taskCompletionRepo;

    public TaskCompletionService(TaskCompletionRepo taskCompletionRepo) {
        this.taskCompletionRepo = taskCompletionRepo;
    }

    public List<TaskCompletionModel> getAllTaskCompletionAccordingToLanguageAndUser(@Nonnull BBackLanguage language,
        @Nonnull UserModel userModel, @Nonnull List<TaskModel> tasks)
    {
        return taskCompletionRepo.findAllByLanguageAndUserAndTaskIn(language, userModel, tasks);
    }

    public List<TaskCompletionModel> getAllByLanguageAndUserAndTaskId(@Nonnull BBackLanguage language,
        @Nonnull UserModel user, long taskId)
    {
        return taskCompletionRepo.findAllByLanguageAndUserAndTaskId(language, user, taskId);
    }

    public void save(@Nonnull TaskCompletionModel taskCompletion) {
        taskCompletionRepo.save(taskCompletion);
    }
}
