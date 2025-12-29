package com.example.moro.app.colormap.repository;

import com.example.moro.app.colormap.entity.ColorMap;
import com.example.moro.app.colormap.entity.UserColorMap;
import com.example.moro.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserColorMapRepository extends JpaRepository<UserColorMap, Long> {
    boolean existsByMemberAndColorMap(Member member, ColorMap colorMap);

    int countByMemberAndUnlockedTrue(Member member);

    List<UserColorMap> findByMemberAndIsRepresentativeTrue(Member member);


}
