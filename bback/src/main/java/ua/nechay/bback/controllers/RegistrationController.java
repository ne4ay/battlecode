package ua.nechay.bback.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.responses.RegistrationException;
import ua.nechay.bback.dto.responses.RegistrationResponse;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.requests.RegistrationRequest;
import ua.nechay.bback.service.UserService;

import java.util.Collections;
import java.util.Optional;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService service) {
        this.userService = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GenericResponse<RegistrationResponse, RegistrationException> register(@RequestBody RegistrationRequest request) {
        Optional<UserModel> existingUser = userService.findByLogin(request.getLogin());
        if (existingUser.isPresent()) {
            return RegistrationResponse.createGenericResponse(false, RegistrationException.USER_WITH_DUPLICATED_LOGIN);
        }
        UserModel user = new UserModel.Builder()
            .setLogin(request.getLogin())
            .setPassword(request.getPassword())
            .setEMail(request.getEmail())
            .setExperience(0)
            .setLevel(1)
            .setRoles(Collections.singleton(BBackUserRole.DEFAULT_USER))
            .build();
        userService.save(user);
        return RegistrationResponse.createGenericResponse(true, null);
    }
}
