package com.example.moro.app.post.dto;

import com.example.moro.app.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String imageUrl;
    private String likecount; //좋아요 개수
    private int shareCount; //공유 횟수
    private Double lat; //위도 경도
    private Double lng;
    //색깔 관련해서 추가해줘야 함...!

    public PostResponseDto(Post post, int likeCount){
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.shareCount = post.getShareCount();
        this.lat = post.getLat();
        this.lng = post.getLng();
        this.likecount = likecount;
    }

}
