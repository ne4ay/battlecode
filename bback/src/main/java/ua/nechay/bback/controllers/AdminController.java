package ua.nechay.bback.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.service.TaskService;

/**
 * @author anechaev
 * @since 02.05.2022
 */
@RestController
@RequestMapping(AdminController.ADMIN_PATH)
public class AdminController {
    public final static String ADMIN_PATH = "/admin";

    private final TaskService taskService;

    public AdminController(TaskService taskService) {
        this.taskService = taskService;
    }
}
