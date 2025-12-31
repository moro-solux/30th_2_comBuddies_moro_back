package com.example.moro.app.post.service;

import com.example.moro.app.colormap.entity.ColorMap;
import com.example.moro.app.colormap.repository.ColorMapRepository;
import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.dto.PostRequestDto;
import com.example.moro.app.post.dto.PostResponseDto;
import com.example.moro.app.post.entity.Post;
import com.example.moro.app.post.entity.PostColor;
import com.example.moro.app.post.repository.LikeRepository;
import com.example.moro.app.post.repository.PostColorRepository;
import com.example.moro.app.post.repository.PostRepository;
import com.example.moro.global.util.ColorExtractor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final PostColorRepository postColorRepository;
    private final ColorExtractor colorExtractor;
    private final ColorMapRepository colorMapRepository;

    //게시물 생성
    public Long createPost(PostRequestDto requestDto, Member member) {
        // post 먼저 생성 및 저장
        Post post = Post.builder()
                .member(member)
                .mainColorId(requestDto.getMainColorId())
                .imageUrl(requestDto.getImageUrl())
                .lat(requestDto.getLat())
                .lng(requestDto.getLng())
                .build();

        Post savedPost = postRepository.save(post);

        // 이미지에서 상위 4개 컬러 추출
        List<Integer> top4ColorIds = colorExtractor.extractTop4Colors(requestDto.getImageUrl());

        //추출된 컬러들을 postcolor 엔티티로 생성해서 저장
        for (Integer colorId : top4ColorIds) {
            //colorid를 통해서 colormap 엔티티를 찾아야 함.
            ColorMap colorMap = colorMapRepository.findById(colorId.longValue())
                    .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 컬러id:" + colorId ));

            //컬러맵과 관계를 맺어 빌더 생성
            PostColor postColor = PostColor.builder()
                    .post(savedPost)   // savedPost와 연결
                    .colormap(colorMap)  // 추출된 ID
                    .ratio(0.25)
                    .build();

            postColorRepository.save(postColor);
        }

        return savedPost.getId();
    }

    //게시물 삭제
    public void deletePost(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 존재하지 않음.id=" + postId));
        if (!post.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("해당 게시물을 삭제할 권한이 없습니다.");
        }
        postRepository.delete(post);
    }

    //게시물 조회
    public PostResponseDto getPost(Long postId) {
        //게시물 존재 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시물 없음"));

        //좋아요 개수 조회
        int likeCount = likeRepository.countByPost(post);

        //해당 게시물의 4게 컬러 조회
        List<Integer> colorIds = postColorRepository.findAllByPost(post).stream()
                .map(postColor -> postColor.getColormap().getColorId().intValue())
                .collect(Collectors.toList());

        //dto에 담아서 반환
        return new PostResponseDto(post, likeCount, colorIds);
    }

    //게시물 공유
    public void sharePost(Long postId) {
        Post post=postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시물 없음"));
        post.increaseShareCount();
    }


}