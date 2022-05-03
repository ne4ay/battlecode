package ua.nechay.bback.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.RegistrationRequest;
import ua.nechay.bback.dto.responses.RegistrationResponseException;
import ua.nechay.bback.dto.responses.RegistrationResponse;
import ua.nechay.bback.service.UserService;

import java.util.Optional;
import java.util.Set;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@RestController
@RequestMapping(RegistrationController.REGISTER_PATH)
public class RegistrationController {
    public static final String REGISTER_PATH = "/register";
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public RegistrationController(UserService service, AuthenticationManager authenticationManager) {
        this.userService = service;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GenericResponse<RegistrationResponse, RegistrationResponseException> register(@RequestBody RegistrationRequest request) {
        Optional<UserModel> existingUser = userService.findByLogin(request.getLogin());
        if (existingUser.isPresent()) {
            return GenericResponse.fromException(RegistrationResponseException.USER_WITH_DUPLICATED_LOGIN);
        }
        UserModel user = new UserModel.Builder()
            .setLogin(request.getLogin())
            .setPassword(request.getPassword())
            .setEMail(request.getEmail())
            .setExperience(0)
            .setLevel(1)
            .setRoles(Set.of(BBackUserRole.DEFAULT_USER, BBackUserRole.GLOBAL_ADMINISTRATOR))
            .build();
        userService.save(user);
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user, request.getPassword(), user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return RegistrationResponse.createGenericResponse(true);
    }
}
