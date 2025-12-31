package com.example.moro.app.colormap.service;

import com.example.moro.app.colormap.dto.ColorDetailResponse;
import com.example.moro.app.colormap.dto.ThemeGroupResponse;
import com.example.moro.app.colormap.entity.UserColorMap;
import com.example.moro.app.colormap.repository.UserColorMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ColorMapService {
    private final UserColorMapRepository userColorMapRepository;

    public List<ThemeGroupResponse> getUserColorMaps(Long memberId) {
        // 모든 색상 정보와 사용자의 해금 상태 한 번에 조회
        List<UserColorMap> userColors = userColorMapRepository.findAllByMemberIdWithColorMap(memberId);

        // 테마 별로 그룹화해 변환
        return userColors.stream()
                .collect(Collectors.groupingBy(ucm -> ucm.getColorMap().getColorTheme()))
                .entrySet().stream()
                .map(entry -> new ThemeGroupResponse(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(ucm -> new ColorDetailResponse(
                                        ucm.getColorMap().getColorId(),
                                        ucm.getColorMap().getHexCode(),
                                        ucm.getPostCount(),
                                        ucm.getUnlocked(),
                                        ucm.getIsRepresentative()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
