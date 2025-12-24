package com.example.moro.app.mission.controller;

import com.example.moro.app.mission.dto.MissionPostRequest;
import com.example.moro.app.mission.dto.MissionPostResponse;
import com.example.moro.app.mission.service.MissionPostService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionPostController {

    private final MissionPostService missionPostService;

    // POST 방식으로 사진과 데이터를 함께 받음
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseTemplate<Long>> uploadMissionPost(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") MissionPostRequest request
    ) {
        Long savedId = missionPostService.saveMissionPost(image, request);
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, savedId);
    }

    @GetMapping("posts/me")
    public ResponseEntity<ApiResponseTemplate<List<MissionPostResponse>>> getMyPosts(
            @AuthenticationPrincipal Long userId // 현재 사용자
    ){
        List<MissionPostResponse> response = missionPostService.getMyPosts(userId);
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, response);
    }

    @GetMapping("posts")
    public ResponseEntity<ApiResponseTemplate<List<MissionPostResponse>>> getAllPosts(){
        List<MissionPostResponse> response = missionPostService.getAllPosts();
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, response);
    }

    /*@GetMapping("posts/freinds")
    public ResponseEntity<ApiResponseTemplate<List<MissionPostResponse>>> getMyFreinds(){}*/
}
