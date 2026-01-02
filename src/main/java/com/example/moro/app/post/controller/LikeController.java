package com.example.moro.app.post.controller;

import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.service.LikeService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}