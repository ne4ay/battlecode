package ua.nechay.bback.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.repo.UserRepo;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * @author anechaev
 * @since 19.04.2022
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepo repository;

    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    public Optional<UserModel> findByLogin(@Nonnull String login) {
        return Optional.ofNullable(repository.findByLogin(login));
    }

    public UserService save(UserModel user) {
        repository.save(user);
        return this;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByLogin(username).orElse(null);
    }
}
