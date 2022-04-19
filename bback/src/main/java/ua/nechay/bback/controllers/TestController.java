package ua.nechay.bback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anechaev
 * @since 27.03.2022
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        System.out.println("lol"); //TODO: clean
        return "Just a simple test content";
    }

    @PostMapping
    public String postTest() {
        System.out.println("testpos"); //TODO: clean
        return "Just a simple test content for post";
    }
}