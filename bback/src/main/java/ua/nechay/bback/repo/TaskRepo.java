package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.BBackTaskStatus;
import ua.nechay.bback.domain.TaskModel;

import java.util.List;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Repository
public interface TaskRepo extends JpaRepository<TaskModel, Long> {

    @Query(value = "SELECT * FROM tasks t"
        + " LEFT JOIN language_to_task l ON t.id = l.task_id"
        + " WHERE l.language = :lang",
        nativeQuery = true)
    List<TaskModel> findAllByLanguage(@Param("lang") String lang);

    @Query(value = "SELECT * FROM tasks t"
        + " LEFT JOIN language_to_task l ON t.id = l.task_id"
        + " WHERE l.language = :lang OFFSET :offset ROWS FETCH NEXT :count ROWS ONLY",
        nativeQuery = true)
    List<TaskModel> findAllByLanguage(@Param("lang") String lang, @Param("offset") int offset, @Param("count") int count);

    @Query(value = "SELECT * FROM tasks t"
        + " LEFT JOIN language_to_task l ON t.id = l.task_id"
        + " WHERE l.language = :lang AND t.status = :status"
        + " OFFSET :offset ROWS FETCH NEXT :count ROWS ONLY",
        nativeQuery = true)
    List<TaskModel> findAllByLanguageAndStatus(@Param("lang") String lang, @Param("status") String status,
        @Param("offset") int offset, @Param("count") int count);

    long countAllByStatusIs(BBackTaskStatus status);
}
