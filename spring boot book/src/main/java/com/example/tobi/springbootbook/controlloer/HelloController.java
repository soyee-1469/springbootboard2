package com.example.tobi.springbootbook.controlloer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello2")//templates 랑 동일명
    public String hello() {
        return "hello";
    }
}
