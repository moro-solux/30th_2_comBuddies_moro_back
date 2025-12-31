package com.example.moro.app.post.dto;

import com.example.moro.app.post.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String imageUrl;
    private String likecount; //좋아요 개수
    private int shareCount; //공유 횟수
    private Double lat; //위도 경도
    private Double lng;
    private List<Integer> colorIds;

    public PostResponseDto(Post post, int likeCount,List<Integer> colorIds) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.shareCount = post.getShareCount();
        this.lat = post.getLat();
        this.lng = post.getLng();
        this.likecount = likecount;
        this.colorIds = colorIds;
    }

}
