package com.example.tobi.springbootbook.Hello;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
