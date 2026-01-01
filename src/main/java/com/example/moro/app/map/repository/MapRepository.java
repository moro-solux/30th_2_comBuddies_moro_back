package com.example.moro.app.map.repository;

import com.example.moro.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapRepository extends JpaRepository<Post,Long> {

    List<Post> findByMember_IdAndLatBetweenAndLngBetween(Long memberId, Double minLat, Double maxLat, Double minLng, Double maxLng);

}
