package com.example.moro.app.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeResponse {

    private long totalCount;
    private SimpleLikerInfo topLiker;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SimpleLikerInfo {
        private Long userId;
        private String nickname;
    }
}