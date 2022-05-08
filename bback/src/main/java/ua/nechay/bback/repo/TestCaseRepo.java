package ua.nechay.bback.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.nechay.bback.domain.TestCaseModel;

import java.util.List;

/**
 * @author anechaev
 * @since 05.05.2022
 */
public interface TestCaseRepo extends JpaRepository<TestCaseModel, Long> {

    @Query(value = "SELECT * FROM test_cases tc"
        + " LEFT JOIN tasks t ON tc.task_id = t.t"
        + " WHERE t.id = :id",
        nativeQuery = true)
    List<TestCaseModel> findAllByTaskId(long id);

    void deleteAllByTaskId(long taskId);
}
