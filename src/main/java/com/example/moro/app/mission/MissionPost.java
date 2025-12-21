package com.example.moro.app.mission;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MissionPost {

    @Id
    @GeneratedValue
    private Long misPostId;  // 미션 게시물 id

    // Mission 테이블과 관계(M:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "mission_id")
    private Mission mission;

    private Long userId;   // 사용자 아이디

    private LocalDateTime createdAt; // 생성일
    private String imageUrl;   // 이미지
    private String detail;   // 상세

    private Double lat;   // 위도
    private Double lng;   // 경도

}
