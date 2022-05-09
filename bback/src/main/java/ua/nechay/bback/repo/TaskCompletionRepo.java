package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.TaskCompletionModel;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.domain.user.UserModel;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@Repository
public interface TaskCompletionRepo extends JpaRepository<TaskCompletionModel, Long> {

    List<TaskCompletionModel> findAllByLanguageAndUserAndTaskIn(@Nonnull BBackLanguage language,
        @Nonnull UserModel user,
        @Nonnull List<TaskModel> tasks);

    List<TaskCompletionModel> findAllByLanguageAndUserAndTaskId(@Nonnull BBackLanguage language, @Nonnull UserModel user, long taskId);
}
