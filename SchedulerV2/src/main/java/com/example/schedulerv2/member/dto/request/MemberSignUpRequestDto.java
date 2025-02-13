package com.example.schedulerv2.member.dto.request;

import lombok.Getter;

@Getter
public class MemberSignUpRequestDto {
    private String username;
    private String password;
    private String email;
}
