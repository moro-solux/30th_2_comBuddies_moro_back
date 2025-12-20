package com.example.moro.app.auth.controller;

import com.example.moro.app.auth.dto.LoginRequest;
import com.example.moro.app.auth.dto.LoginResponse;
import com.example.moro.app.auth.service.AuthService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 및 권한 부여를 담당하는 컨트롤러
 * 소셜 로그인 및 토큰 발급관련 api를 처리함
 */

@Tag(name = "Auth", description = "인증 관련 API (로그인 등)") // Swagger 상단 그룹 이름
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "구글 로그인/회원가입",
            description = "구글에서 받은 이메일과 이름을 통해 로그인을 진행하고 JWT 토큰을 발급합니다."
    )

    /**
     * 구글 인증 성공 후 로그인을 처리하는 엔드포인트
     * 프론트로부터 받은 구글 사용자 정보를 바탕으로 회원가입 또는 로그인을 수행하고 jwt를 반환함.
     * @param request 구글에서 전달받은 사용자 정보 (이메일, 이름 등)을 담은 객체
     * @return 발급된 jwt 토큰 및 회원 정보를 포함한 LoginResponse
     */

    @PostMapping("/login")
    public ResponseEntity<ApiResponseTemplate<LoginResponse>> login(@RequestBody LoginRequest request) {
        //비즈니스 로직 (회원 확인 및 토큰 생성)은 AuthService에 위임함.
        LoginResponse response = authService.login(request.getEmail(), request.getName());
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, response);
    }
}