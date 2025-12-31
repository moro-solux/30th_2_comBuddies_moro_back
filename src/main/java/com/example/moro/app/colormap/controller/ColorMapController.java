package com.example.moro.app.colormap.controller;

import com.example.moro.app.colormap.dto.ThemeGroupResponse;
import com.example.moro.app.colormap.service.ColorMapService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colormaps")
@RequiredArgsConstructor
public class ColorMapController {
    private final ColorMapService colorMapService;

    @GetMapping("")
    public ResponseEntity<ApiResponseTemplate<List<ThemeGroupResponse>>> getMyColorMaps(
            @AuthenticationPrincipal Long userId){
        // 현재 로그인한 사용자 ID
        List<ThemeGroupResponse> response = colorMapService.getUserColorMaps(userId);

        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, response);
    }
}
