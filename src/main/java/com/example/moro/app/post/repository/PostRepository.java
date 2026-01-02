package com.example.moro.app.post.repository;

import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    int countByMemberId(Long memberId);

    Page<Post> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);

    Page<Post> findByMemberAndMainColorIdInOrderByCreatedAtDesc(Member member, List<Integer> colorIds, Pageable pageable);

    Page<Post> findByMemberAndMainColorIdOrderByCreatedAtDesc(Member member, Integer colorId, Pageable pageable);

    // ===== 홈 피드 (인스타그램 스타일 피드) =====
    // 팔로우한 사용자들의 게시물 + 전체 공개 사용자의 게시물을 최신순으로 조회
    @Query("""
        SELECT DISTINCT p FROM Post p
        WHERE (p.member IN (
            SELECT f.following FROM Follow f
            WHERE f.follower.id = :currentUserId AND f.status = 'ACCEPTED'
        ) OR p.member.isPublic = true)
        AND p.member.id != :currentUserId
        ORDER BY p.createdAt DESC
        """)
    Page<Post> findHomeFeedPosts(@Param("currentUserId") Long currentUserId, Pageable pageable);
}
