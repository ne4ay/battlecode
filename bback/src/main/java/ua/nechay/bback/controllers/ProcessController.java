package ua.nechay.bback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.dto.requests.ProcessTaskRequest;
import ua.nechay.bback.service.TaskService;

/**
 * @author anechaev
 * @since 06.05.2022
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired private final TaskService taskService;

    public ProcessController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public String processTask(@RequestBody ProcessTaskRequest request) {
        return null;
    }
}
