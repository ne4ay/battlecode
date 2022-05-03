package ua.nechay.bback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.ProfileInfoResponse;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;

import static ua.nechay.bback.utils.BBackAuthUtils.getUserFromAuthentication;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@RestController
@RequestMapping("/profile")
public class ProfileInfoController {

    private final UserService userService;

    public ProfileInfoController(@Nonnull UserService service) {
        this.userService = service;
    }

    @GetMapping(value = "/basic")
    public @ResponseBody GenericResponse<ProfileInfoResponse, GeneralResponseException> getBasicInfo() {
        return getUserFromAuthentication()
            .map(ProfileInfoResponse::createGenericResponse)
            .orElse(GenericResponse.fromException(GeneralResponseException.UNAUTHORIZED));
    }
}
