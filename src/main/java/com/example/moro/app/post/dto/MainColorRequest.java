package com.example.moro.app.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

//메인 컬러 사용자 선택 요청
@Getter
@NoArgsConstructor
public class MainColorRequest {
    private Integer selectedColorId;
}
