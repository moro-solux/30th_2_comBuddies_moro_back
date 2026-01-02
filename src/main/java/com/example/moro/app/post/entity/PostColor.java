package com.example.moro.app.post.entity;

import com.example.moro.app.colormap.entity.ColorMap;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class PostColor {
    @Id
    @Column(name = "pColorId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "colorId")
    private ColorMap colormap;

    @Column
    private Double ratio;
}
