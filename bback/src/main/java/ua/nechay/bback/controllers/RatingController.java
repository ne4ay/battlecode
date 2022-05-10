package ua.nechay.bback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.responses.GeneralResponseException;
import ua.nechay.bback.dto.responses.RatingUsersResponse;
import ua.nechay.bback.service.UserService;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 10.05.2022
 */
@RestController
@RequestMapping("/rating")
public class RatingController {

    private final UserService userService;

    public RatingController(@Nonnull UserService service) {
        this.userService = service;
    }

    @GetMapping(params = {"page", "size"})
    public @ResponseBody
    GenericResponse<RatingUsersResponse, GeneralResponseException>
    getRating(@RequestParam("page") int page, @RequestParam("size") int size) {
        if (size <= 0) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        long countOfOrdinaryUsers = userService.getCountOfUsersByRole(BBackUserRole.DEFAULT_USER);
        int countOfPages = (int) (countOfOrdinaryUsers / size + 1);
        if (page - 1 > countOfPages) {
            return GenericResponse.fromException(GeneralResponseException.WRONG_REQUEST);
        }
        List<UserModel> ordinaryUsers = userService.getAllUsersWithOnlyRoleSortingByExpDesc(BBackUserRole.DEFAULT_USER, page - 1, size);
        return RatingUsersResponse.createGenericResponse(ordinaryUsers, countOfPages, size);
    }
}
