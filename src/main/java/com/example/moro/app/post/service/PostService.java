package com.example.moro.app.post.service;

import com.example.moro.app.colormap.entity.ColorMap;
import com.example.moro.app.colormap.repository.ColorMapRepository;
import com.example.moro.app.member.entity.Member;
import com.example.moro.app.post.dto.PostRequestDto;
import com.example.moro.app.post.dto.PostResponseDto;
import com.example.moro.app.post.entity.Post;
import com.example.moro.app.post.entity.PostColor;
import com.example.moro.app.post.repository.CommentRepository;
import com.example.moro.app.post.repository.LikeRepository;
import com.example.moro.app.post.repository.PostColorRepository;
import com.example.moro.app.post.repository.PostRepository;
import com.example.moro.global.util.ColorExtractor;
import com.example.moro.global.util.ColorExtractor.ColorAnalysisResult;
import com.example.moro.global.common.dto.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final PostColorRepository postColorRepository;
    private final CommentRepository commentRepository;
    private final ColorExtractor colorExtractor;
    private final ColorMapRepository colorMapRepository;

    // 1. 게시물 생성
    public Long createPost(PostRequestDto requestDto, Member member) {
        // 이미지 색상 분석 (비율 포함)
        List<ColorAnalysisResult> colorResults = colorExtractor.extractTop4ColorsWithRatio(requestDto.getImageUrl());

        // 메인 컬러 지정
        ColorAnalysisResult mainColor = colorResults.get(0);

        // Post 엔티티 생성 (메인 컬러는 분석 결과 사용) =====
        Post post = Post.builder()
                .member(member)
                .mainColorId(mainColor.getColorId())  // ✅ 이미지 분석 결과 사용
                .imageUrl(requestDto.getImageUrl())
                .lat(requestDto.getLat())
                .lng(requestDto.getLng())
                .build();

        Post savedPost = postRepository.save(post);

        // ===== 4. PostColor 엔티티들 저장 (실제 비율 사용) =====
        // 기존: ratio(0.25) - 고정값
        // 개선: ratio(result.getRatio()) - 실제 계산된 비율
        for (ColorAnalysisResult result : colorResults) {
            ColorMap colorMap = colorMapRepository.findById((long) result.getColorId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬러id:" + result.getColorId()));

            PostColor postColor = PostColor.builder()
                    .post(savedPost)
                    .colormap(colorMap)
                    .ratio(result.getRatio())  // ✅ 실제 비율 사용
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
    @Transactional
    public PostResponseDto getPost(Long postId) {
        //게시물 존재 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시물 없음"));

        //좋아요 개수 조회
        int likeCount = likeRepository.countByPost(post);

        //댓글 개수 조회
        int commentCount = commentRepository.countByPost(post);

        //색상 정보 조회
        List<PostResponseDto.ColorInfo> colors = getColorInfos(post);

        //사용자 정보 조회
        String userName = post.getMember().getUserName();
        String userProfileImage = getUserProfileImage(post.getMember());

        //확장된 DTO에 담아서 반환
        return new PostResponseDto(post, likeCount, commentCount, colors, userName, userProfileImage);
    }

    //게시물 공유
    public void sharePost(Long postId) {
        Post post=postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시물 없음"));
        post.increaseShareCount();
    }

    // 헬퍼 메서드들 =====

    // 게시물의 색상 정보 조회
    private List<PostResponseDto.ColorInfo> getColorInfos(Post post) {
        return postColorRepository.findAllByPost(post).stream()
                .map(postColor -> new PostResponseDto.ColorInfo(
                        postColor.getColormap().getColorId().intValue(), // Long → int 변환
                        postColor.getColormap().getHexCode(),
                        postColor.getRatio()
                ))
                .collect(Collectors.toList());
    }

    // 사용자 프로필 이미지 조회 (임시: 대표 색상 사용)
    private String getUserProfileImage(Member member) {
        // TODO: 나중에 Member 엔티티에 profileImageUrl 필드가 추가되면 수정
        // 현재는 임시로 사용자의 대표 색상을 프로필 이미지로 사용
        return member.getUserColorHex();
    }

    // ===== 홈 피드 (인스타그램 스타일 피드) =====
    // 팔로우한 사용자들의 게시물 + 전체 공개 사용자의 게시물을 최신순으로 조회 (무한 스크롤 지원)
    @Transactional
    public PageResponse<PostResponseDto> getHomeFeed(Long currentUserId, Pageable pageable) {
        Page<Post> posts = postRepository.findHomeFeedPosts(currentUserId, pageable);

        Page<PostResponseDto> postResponseDtos = posts.map(post -> {
            // 좋아요 개수 조회
            int likeCount = likeRepository.countByPost(post);

            // 댓글 개수 조회
            int commentCount = commentRepository.countByPost(post);

            // 색상 정보 조회
            List<PostResponseDto.ColorInfo> colors = getColorInfos(post);

            // 사용자 정보 조회
            String userName = post.getMember().getUserName();
            String userProfileImage = getUserProfileImage(post.getMember());

            // DTO로 변환
            return new PostResponseDto(post, likeCount, commentCount, colors, userName, userProfileImage);
        });

        return PageResponse.from(postResponseDtos);
    }


}