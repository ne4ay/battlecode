package ua.nechay.bback.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.ProfileInfoResponse;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@RestController
@RequestMapping("/profile")
public class AuthSecuredController {

    private final UserService userService;

    public AuthSecuredController(@Nonnull UserService service) {
        this.userService = service;
    }

    @GetMapping(value = "/basic")
    public @ResponseBody GenericResponse<ProfileInfoResponse, GeneralResponseException> getBasicInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return GenericResponse.fromException(GeneralResponseException.UNAUTHORIZED);
        }
        String authName = authentication.getName();
        return userService.findByLogin(authName)
            .map(ProfileInfoResponse::createGenericResponse)
            .orElse(GenericResponse.fromException(GeneralResponseException.SERVER_ERROR));
    }
}
