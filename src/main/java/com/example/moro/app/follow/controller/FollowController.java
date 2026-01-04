package com.example.moro.app.follow.controller;

import com.example.moro.app.follow.service.FollowService;
import com.example.moro.app.follow.dto.FollowRequestDto;
import com.example.moro.app.follow.dto.FollowResponseDto;
import com.example.moro.app.follow.entity.Follow;
import com.example.moro.app.member.entity.Member;
import com.example.moro.app.member.service.MemberService;
import com.example.moro.global.common.ApiResponseTemplate;
import com.example.moro.global.common.ErrorCode;
import com.example.moro.global.common.SuccessCode;
import com.example.moro.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.moro.global.util.SecurityUtil.getCurrentMember;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;
    private final MemberService memberService;


    @PostMapping
    public ResponseEntity<?> follow(@RequestBody FollowRequestDto request) {

        Member me = getCurrentMember();

        Follow follow = followService.requestFollow(me.getId(), request.getTargetUserId());

        FollowResponseDto response = new FollowResponseDto(follow.getFollowId(), follow.getStatus());

        return ApiResponseTemplate.success(SuccessCode.OPERATION_SUCCESSFUL, response);
    }

    @DeleteMapping("/{targetUserId}")
    public ResponseEntity<?> removeFollow(@PathVariable Long targetUserId) {

        Member member = getCurrentMember();
        followService.removeByFollower(member.getId(), targetUserId);

        return ApiResponseTemplate.success(SuccessCode.RESOURCE_DELETED, null);
    }

    @PatchMapping("/{followId}/accept")
    public ResponseEntity<?> acceptFollow(@PathVariable Long followId) {

        Member member = getCurrentMember();
        FollowResponseDto response = followService.approveFollow(followId, member.getId());

        return ApiResponseTemplate.success(SuccessCode.OPERATION_SUCCESSFUL, response);
    }

    @DeleteMapping("/{followId}/reject")
    public ResponseEntity<?> reject(@PathVariable Long followId) {
        Member me = getCurrentMember();
        followService.rejectByFollowing(followId, me.getId());
        return ApiResponseTemplate.success(SuccessCode.RESOURCE_DELETED, null);
    }





}
