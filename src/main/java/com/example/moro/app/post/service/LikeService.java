package com.example.moro.app.post.service;

import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.entity.Like;
import com.example.moro.app.post.entity.Post;
import com.example.moro.app.post.repository.LikeRepository;
import com.example.moro.app.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    //좋아요 토글기능
    public void toggleLike(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물 없음"));

        // 이미 좋아요를 눌렀는지 확인
        Optional<Like> postLike = likeRepository.findByPostAndMember(post, member);

        if (postLike.isPresent()) {
            likeRepository.delete(postLike.get()); // 있으면 취소
        } else {
            likeRepository.save(Like.builder() // 없으면 추가
                    .post(post)
                    .member(member)
                    .build());
        }
    }
}