package com.example.moro.app.mission;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;  // 미션 id

    private String missionTitle;   // 미션 제목

    private LocalDateTime createdAt;   // 미션 생성 시간
}
