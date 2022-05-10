package ua.nechay.bback.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.repo.UserRepo;
import ua.nechay.bback.repo.UserRoleRepo;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * @author anechaev
 * @since 19.04.2022
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;

    public UserService(UserRepo repository, UserRoleRepo userRoleRepo) {
        this.userRepo = repository;
        this.userRoleRepo = userRoleRepo;
    }

    public Optional<UserModel> findByLogin(@Nonnull String login) {
        return Optional.ofNullable(userRepo.findByLogin(login));
    }

    public Optional<UserModel> findById(long id) {
        return userRepo.findById(id);
    }

    public long getCountOfUsersByRole(BBackUserRole role) {
        return userRepo.countAllByRoleIs(role.name());
    }

    public long getCountOfAllUsers() {
        return userRepo.count();
    }

    public List<UserModel> getAllUsersWithOnlyRoleSortingByExpDesc(BBackUserRole role, int page, int size) {
        return userRepo.findAllUsersByRole(role.name(), page, size);
    }

    public List<UserModel> getAllUsers(int page, int size) {
        return userRepo.findAll(PageRequest.of(page, size)).toList();
    }

    public UserService save(@Nonnull UserModel user) {
        userRepo.save(user);
        userRoleRepo.saveAll(user.getRoleModels());
        return this;
    }

    public UserService update(@Nonnull UserModel user) {
        userRoleRepo.deleteAllByUserId(user.getId());
        return save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByLogin(username).orElse(null);
    }
}
