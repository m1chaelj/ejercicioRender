package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }
}
