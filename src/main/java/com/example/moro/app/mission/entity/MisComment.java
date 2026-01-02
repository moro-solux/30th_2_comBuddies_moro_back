package com.example.moro.app.mission.entity;

import jakarta.persistence.*;

public class MisComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long misCommentId;  // 주키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "misPostId")   // fk 연결
    private MissionPost missionPost;

    private String misContent;
}
