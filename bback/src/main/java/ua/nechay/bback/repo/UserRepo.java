package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.user.UserModel;

/**
 * @author anechaev
 * @since 19.04.2022
 */
@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    UserModel findByLogin(String username);
}
