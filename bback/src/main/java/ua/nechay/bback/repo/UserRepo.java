package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;

import java.util.List;

/**
 * @author anechaev
 * @since 19.04.2022
 */
@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

    UserModel findByLogin(String username);

    @Query(value = "SELECT * FROM public.users AS u"
        + " JOIN ("
        + " SELECT urole.user_id, string_agg(urole.role, ', ') "
        + " OVER (PARTITION BY user_id) AS role"
        + " FROM public.user_role AS urole"
        + " ) AS ur ON u.id = ur.user_id"
        + " WHERE ur.role=:role"
        + " ORDER BY u.experience desc"
        + " OFFSET :offset ROWS FETCH NEXT :count ROWS ONLY",
        nativeQuery = true)
    List<UserModel> findAllUsersByRole(@Param("role") String role, @Param("offset") int offset, @Param("count") int count);

    @Query(value = "SELECT COUNT(*) FROM public.users AS u"
        + " JOIN ("
        + " SELECT urole.user_id, string_agg(urole.role, ', ') "
        + " OVER (PARTITION BY user_id) AS role"
        + " FROM public.user_role AS urole"
        + " ) AS ur ON u.id = ur.user_id"
        + " WHERE ur.role=:role",
        nativeQuery = true)
    long countAllByRoleIs(@Param("role") String role);
}
