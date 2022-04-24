package ua.nechay.bback.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@RestController
@RequestMapping("/auth")
public class AuthSecuredController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthSecuredController(@Nonnull UserService service, @Nonnull AuthenticationManager authenticationManager) {
        this.userService = service;
        this.authenticationManager = authenticationManager;
    }
}
