package ua.nechay.bback.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.nechay.bback.domain.TestCaseModel;

/**
 * @author anechaev
 * @since 05.05.2022
 */
public interface TestCaseRepo extends JpaRepository<TestCaseModel, Long> {


    void deleteAllByTaskId(long taskId);
}
