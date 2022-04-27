package ua.nechay.bback.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.responses.LoginResponseException;
import ua.nechay.bback.service.UserService;

import java.util.Optional;

/**
 * @author anechaev
 * @since 20.04.2022
 */
@Component
public class BBackAuthenticationManager implements AuthenticationManager {

    private final UserService userService;

    public BBackAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return authentication;
        }
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<UserModel> maybeUser = userService.findByLogin(name);
        if (maybeUser.isEmpty()) {
            throw new BadCredentialsException(LoginResponseException.WRONG_CREDENTIALS.name());
        }
        UserModel userModel = maybeUser.get();
        if (!userModel.getPassword().equals(password)) {
            throw new BadCredentialsException(LoginResponseException.WRONG_CREDENTIALS.name());
        }
        return new UsernamePasswordAuthenticationToken(userModel, password, userModel.getAuthorities());
    }
}
