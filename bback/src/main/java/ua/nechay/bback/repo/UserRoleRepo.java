package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.user.UserRoleModel;

/**
 * @author anechaev
 * @since 10.05.2022
 */
@Repository
public interface UserRoleRepo extends JpaRepository<UserRoleModel, Long> {

    void deleteAllByUserId(long userId);
}
