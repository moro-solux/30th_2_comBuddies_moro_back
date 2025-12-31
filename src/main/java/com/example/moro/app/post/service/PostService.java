package com.example.moro.app.post.service;

import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.dto.PostRequestDto;
import com.example.moro.app.post.dto.PostResponseDto;
import com.example.moro.app.post.entity.Post;
import com.example.moro.app.post.repository.LikeRepository;
import com.example.moro.app.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    //게시물 생성
    @Transactional
    public Long createPost(PostRequestDto requestDto, Member member) {
        // 엔티티 생성 시 전달받은 member 객체를 함께 빌더에 주입
        Post post = Post.builder()
                .member(member)
                .mainColorId(requestDto.getMainColorId())
                .imageUrl(requestDto.getImageUrl())
                .lat(requestDto.getLat())
                .lng(requestDto.getLng())
                .build();

        return postRepository.save(post).getId();
    }

    //게시물 삭제
    @Transactional
    public void deletePost(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 존재하지 않음.id=" + postId));
        if (!post.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("해당 게시물을 삭제할 권한이 없습니다.");
        }
        postRepository.delete(post);
    }

    //게시물 조회
    @Transactional
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시물 없음"));

        int likeCount = likeRepository.countByPost(post);

        return new PostResponseDto(post, likeCount);
    }

    //게시물 공유
    @Transactional
    public void sharePost(Long postId) {
        Post post=postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시물 없음"));
        post.increaseShareCount();
    }


}