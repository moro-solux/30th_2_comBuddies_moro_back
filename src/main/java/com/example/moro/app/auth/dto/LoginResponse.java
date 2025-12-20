package com.example.moro.app.auth.dto;

import com.example.moro.app.member.dto.MemberResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private MemberResponse member;
    private String token;
}
