package com.example.tobi.springnotice.controller;

import com.example.tobi.springnotice.dto.MemberCreateRequestDTO;
import com.example.tobi.springnotice.dto.MemberResponseDTO;
import com.example.tobi.springnotice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public String createUser(@RequestBody MemberCreateRequestDTO request) {
        userService.createUser(request.toUser());
        return "redirect:/signup";
    }

    //아이디/비번 입력페이지
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/login")
    public String loginUser(
            @RequestParam("userid") String userid,
            @RequestParam("password") String password,
            Model model
    ) {
        model.addAttribute(
                "user",
                MemberResponseDTO.builder()
                        .userid(userid)
                        .password(password)
                        .build()
        );
        return "loginok";
    }


    //목록출력
    @GetMapping("/notice")
    public String notice() {
        return "notice";
    }


}
