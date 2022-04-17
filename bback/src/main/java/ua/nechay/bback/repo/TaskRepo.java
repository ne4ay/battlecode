package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.TaskModel;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Repository
public interface TaskRepo extends JpaRepository<TaskModel, Long> {

}
