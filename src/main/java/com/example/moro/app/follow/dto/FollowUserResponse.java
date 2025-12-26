package com.example.moro.app.follow.dto;

import com.example.moro.app.follow.entity.Follow;
import com.example.moro.app.follow.entity.FollowStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowSearchResponse {
    private Long followId;
    private FollowStatus status;

    private Long userId;
    private String userName;

    public static FollowSearchResponse fromFollower(Follow follow) {
        return FollowSearchResponse.builder()
                .followId(follow.getFollowId())
                .status(follow.getStatus())
                .userId(follow.getFollower().getId())
                .userName(follow.getFollower().getUserName())
                .build();
    }

    public static FollowSearchResponse fromFollowing(Follow follow) {
        return FollowSearchResponse.builder()
                .followId(follow.getFollowId())
                .status(follow.getStatus())
                .userId(follow.getFollowing().getId())
                .userName(follow.getFollowing().getUserName())
                .build();
    }
}
