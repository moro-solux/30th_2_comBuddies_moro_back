package com.example.moro.app.auth.handler;

import com.example.moro.app.auth.dto.LoginResponse;
import com.example.moro.app.auth.service.AuthService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 * OAuth2 로그인 성공 시 실행되는 핸들러
 * 구글로부터 받은 사용자 정보를 우리 서비스의 회원 시스템과 연결하고 JWT를 발급함
 */

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthService authService;
    private final ObjectMapper objectMapper;
    // 앱 스킴 직접 정의
    private static final String FRONTEND_URL = "http://localhost:3000/oauth2/redirect";
    private static final String ANDROID_SCHEME = "moro://auth/oauth2/redirect";
    private static final String IOS_SCHEME = "moro://auth/oauth2/redirect";


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // [Step 1] 구글 사용자 정보 추출
        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");

        // [Step 2] 로그인 처리 및 데이터 생성
        LoginResponse loginResponse = authService.handleOAuthLogin(email);

        // 플랫폼 감지 및 리다이렉트 url 생성
        String platform = detectPlatform(request);
        String targetUrl = buildRedirectUrl(loginResponse, platform);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String detectPlatform(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            if (userAgent.contains("Android")) return "ANDROID";
            if (userAgent.contains("iPhone") || userAgent.contains("iPad")) return "IOS";
        }
        return "WEB";
    }

    private String buildRedirectUrl(LoginResponse loginResponse, String platform) {
        String baseUrl;
        switch (platform) {
            case "ANDROID":
                baseUrl = ANDROID_SCHEME;
                break;
            case "IOS":
                baseUrl = IOS_SCHEME;
                break;
            default:
                baseUrl = FRONTEND_URL;
                break;
        }
        // baseUrl을 사용해서 올바른 URL 생성
        return UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("token", loginResponse.getToken())
                .queryParam("needsNameSetup", loginResponse.isNeedsNameSetup())
                .queryParam("tempEmail", loginResponse.getTempEmail())
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
    }


    /*
        // [Step 3] 프론트엔드로 리다이렉트 (수정된 부분)
        String targetUrl = org.springframework.web.util.UriComponentsBuilder
                .fromUriString("http://localhost:3000/oauth2/redirect") // 프론트엔드 주소
                .queryParam("token", loginResponse.getToken())
                .queryParam("needsNameSetup", loginResponse.isNeedsNameSetup())
                .queryParam("tempEmail", loginResponse.getTempEmail())
                .build()
                .encode(java.nio.charset.StandardCharsets.UTF_8)
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        // [Step 3] 컨벤션 적용
        var apiResponse = ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, loginResponse);

        // [Step 4] 공통 규격으로 설정된 JSON 응답 내보내기
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

         */

}

