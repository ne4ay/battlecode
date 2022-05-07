package ua.nechay.bevaluator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anechaev
 * @since 28.04.2022
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "Test message of evaluator";
    }
}
