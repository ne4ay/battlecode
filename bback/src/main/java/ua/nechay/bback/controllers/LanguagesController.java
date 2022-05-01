package ua.nechay.bback.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.LanguageTO;
import ua.nechay.bback.dto.responses.LanguagesResponse;
import ua.nechay.bback.service.TaskService;

import java.util.Arrays;
import java.util.List;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@RestController
@RequestMapping("/languages")
public class LanguagesController {

    private final TaskService taskService;

    public LanguagesController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public @ResponseBody GenericResponse<LanguagesResponse, GeneralResponseException> getAllLanguages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return GenericResponse.fromException(GeneralResponseException.UNAUTHORIZED);
        }
        List<LanguageTO> langs = Arrays.stream(BBackLanguage.values())
            .map(lang -> new LanguageTO(lang.getName(), taskService.getCountOfTheTasksOnLanguage(lang)))
            .toList();
        return LanguagesResponse.createGenericResponse(langs);
    }
}
