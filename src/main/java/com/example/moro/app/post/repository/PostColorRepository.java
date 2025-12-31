package com.example.moro.app.post.repository;

import com.example.moro.app.post.entity.Post;
import com.example.moro.app.post.entity.PostColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostColorRepository extends JpaRepository<PostColor, Integer> {
    // 특정 게시물의 모든 색상을 가져오는 쿼리 메서드 (조회 시 필요)
    List<PostColor> findAllByPost(Post post);
}