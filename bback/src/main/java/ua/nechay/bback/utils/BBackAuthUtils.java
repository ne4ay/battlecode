package ua.nechay.bback.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.nechay.bback.domain.user.UserModel;

import java.util.Optional;

/**
 * @author anechaev
 * @since 27.04.2022
 */
public final class BBackAuthUtils {

    private BBackAuthUtils() {

    }

    public static Optional<UserModel> getUserFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserModel user)) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
