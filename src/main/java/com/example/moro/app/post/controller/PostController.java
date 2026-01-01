package com.example.moro.app.post.controller;

import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.dto.PostRequestDto;
import com.example.moro.app.post.dto.PostResponseDto;
import com.example.moro.app.post.service.PostService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.SuccessCode;
import com.example.moro.global.common.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    //게시물 생성
    @PostMapping
    public ResponseEntity<ApiResponseTemplate<Long>> createPost(@RequestBody PostRequestDto requestDto,
                                           @AuthenticationPrincipal Member member) {
        Long postId = postService.createPost(requestDto, member);
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_CREATED,postId);
    }

    // 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponseTemplate<Void>> deletePost(@PathVariable Long postId, @AuthenticationPrincipal Member member) {

        postService.deletePost(postId, member);
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_DELETED, null);
    }

    //게시물 공유
    @PostMapping("/{postId}/share")
    public ResponseEntity<ApiResponseTemplate<Void>> sharePost(@PathVariable Long postId) {
        postService.sharePost(postId);
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_UPDATED, null);
    }

    // ===== 홈 피드 (인스타그램 스타일 피드) =====
    // 팔로우한 사용자들의 게시물 + 전체 공개 사용자의 게시물을 최신순으로 조회 (무한 스크롤 지원)
    @GetMapping("/feed")
    public ResponseEntity<ApiResponseTemplate<PageResponse<PostResponseDto>>> getHomeFeed(
            @AuthenticationPrincipal Member member,
            @PageableDefault(size = 20) Pageable pageable) {

        PageResponse<PostResponseDto> feed = postService.getHomeFeed(member.getId(), pageable);
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_RETRIEVED, feed);
    }
}
