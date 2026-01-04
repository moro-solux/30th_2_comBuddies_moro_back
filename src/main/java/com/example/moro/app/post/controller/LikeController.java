package com.example.moro.app.post.controller;

import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.dto.PostLikeResponse;
import com.example.moro.app.post.service.LikeService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<ApiResponseTemplate<Void>> toggleLike(@PathVariable Long postId,
                                                                @AuthenticationPrincipal Member member) {
        likeService.toggleLike(postId, member);
        // 좋아요 토글 성공시에
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_UPDATED, null);
    }

    @Operation(summary = "게시물 좋아요 정보 조회", description = "좋아요 총 개수와 대표 유저 정보를 조회합니다. ('000 외 N명' UI용)")
    @GetMapping
    public ResponseEntity<ApiResponseTemplate<PostLikeResponse>> getPostLikeInfo(@PathVariable Long postId) {

        PostLikeResponse response = likeService.getPostLikeInfo(postId);

        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, response);
    }
}