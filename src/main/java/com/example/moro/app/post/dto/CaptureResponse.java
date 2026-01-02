package com.example.moro.app.post.dto;

import lombok.Getter;

import java.util.List;

//게시물 저장 이전에 미리보기 응답
@Getter
public class CaptureResponse {
    private Long draftId;
    private List<PostResponseDto.ColorInfo> top4Colors;
    private Integer autoSelectedMainColor;

    public CaptureResponse(Long draftId, List<PostResponseDto.ColorInfo> top4Colors, Integer autoSelectedMainColor) {
        this.draftId = draftId;
        this.top4Colors = top4Colors;
        this.autoSelectedMainColor = autoSelectedMainColor;
    }
}
