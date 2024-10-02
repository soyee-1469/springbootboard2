package com.example.tobi.springnotice.dto;

import com.example.tobi.springnotice.model.User;
import lombok.Getter;

@Getter
public class MemberCreateRequestDTO {
    private String userid;
    private String password;
    private String email;
    private String name;
    private int tel;

    public User toUser() {
        return User.builder()
                .userid(userid)
                .password(password)
                .email(email)
                .name(name)
                .tel(tel)
                .build();
    }
}