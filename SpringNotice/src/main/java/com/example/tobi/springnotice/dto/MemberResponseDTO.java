package com.example.tobi.springnotice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDTO {
    private int no;
    private String userid;
    private String password;
    private String email;
    private String name;
    private int tel;
}