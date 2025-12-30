package com.example.moro.app.member.dto;

import com.example.moro.global.common.dto.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserFeedListWrapper {
    private String viewType;
    private PageResponse<UserFeedResponse> page;
}
