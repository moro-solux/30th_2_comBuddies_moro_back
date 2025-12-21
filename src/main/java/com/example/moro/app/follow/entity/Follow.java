package com.example.moro.app.follow.entity;

import com.example.moro.app.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_follower_following",
                        columnNames = {"follower_id", "following_id"}
                )
        }
)
public class Follow{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private Member following;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FollowStatus status;

    public static Follow create(Member follower, Member following) {
        Follow follow = new Follow();
        follow.follower = follower;
        follow.following = following;
        follow.status = following.canBeFollowedDirectly() ? FollowStatus.ACCEPTED : FollowStatus.PENDING;
        return follow;
    }

    public void accept() {
        this.status = FollowStatus.ACCEPTED;
    }

    public void reject() {
        this.status = FollowStatus.REJECTED;
    }
}
