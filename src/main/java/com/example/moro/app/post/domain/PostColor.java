package com.example.moro.app.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostColor {
    @Id
    @Column(name = "pColorId")
    private String id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    //추후에 color 테이블 만들었을 때 꼭 잘 연결되는지 확인!!!
    //@ManyToOne
    //@JoinColumn(name = "colorId")
    //private ColorMap colormap;

    @Column
    private Double ratio;
}
