package ua.nechay.bback.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.LoginRequest;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.LoginResponse;
import ua.nechay.bback.dto.responses.LoginResponseException;
import ua.nechay.bback.dto.responses.LogoutResponse;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@RestController
public class LoginController {
    private static final String LOGIN_PATH = "/login";
    private static final String LOGOUT_PATH = "/getout";
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public LoginController(@Nonnull UserService service, @Nonnull AuthenticationManager authenticationManager) {
        this.userService = service;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(LOGIN_PATH)
    public @ResponseBody GenericResponse<LoginResponse, LoginResponseException> login(@RequestBody LoginRequest request) {
        String login = request.getLogin();
        String password = request.getPassword();
        Optional<UserModel> maybeUser = userService.findByLogin(login);
        if (maybeUser.isEmpty()) {
            return GenericResponse.fromException(LoginResponseException.WRONG_CREDENTIALS);
        }
        UserModel userModel = maybeUser.get();
        if (!userModel.getPassword().equals(password)) {
            return GenericResponse.fromException(LoginResponseException.WRONG_CREDENTIALS);
        }
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userModel, password, userModel.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return LoginResponse.createGenericResponse(true);
    }

    @PostMapping(LOGOUT_PATH)
    public @ResponseBody GenericResponse<LogoutResponse, GeneralResponseException> logout(HttpSession session) {
        if (session == null) {
            return LogoutResponse.createGenericResponse(true);
        }
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
        context.setAuthentication(null);
        return LogoutResponse.createGenericResponse(true);

    }

}
