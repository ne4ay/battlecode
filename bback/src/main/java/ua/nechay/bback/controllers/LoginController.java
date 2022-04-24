package ua.nechay.bback.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.LoginRequest;
import ua.nechay.bback.dto.responses.LoginException;
import ua.nechay.bback.dto.responses.LoginResponse;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public LoginController(@Nonnull UserService service, @Nonnull AuthenticationManager authenticationManager) {
        this.userService = service;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public @ResponseBody GenericResponse<LoginResponse, LoginException> login(@RequestBody LoginRequest request) {
        String login = request.getLogin();
        String password = request.getPassword();
        Optional<UserModel> maybeUser = userService.findByLogin(login);
        if (maybeUser.isEmpty()) {
            return LoginResponse.createGenericResponse(false, LoginException.WRONG_CREDENTIALS);
        }
        UserModel userModel = maybeUser.get();
        if (!userModel.getPassword().equals(password)) {
            return LoginResponse.createGenericResponse(false, LoginException.WRONG_CREDENTIALS);
        }
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return LoginResponse.createGenericResponse(true, null);
    }

    private boolean isAuthenticated(@Nullable Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
