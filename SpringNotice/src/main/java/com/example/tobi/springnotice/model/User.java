package com.example.tobi.springnotice.model;

import com.example.tobi.springnotice.dto.MemberResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder //생성자대신 쓰기위해
public class User {
    private int no;
    private String userid;
    private String password;
    private String email;
    private String name;
    private int tel;

    public MemberResponseDTO toMemberResponseDTO() {
        return MemberResponseDTO.builder()
                .no(no)
                .userid(userid)
                .password(password)
                .email(email)
                .name(name)
                .tel(tel)
                .build();
    }
}
