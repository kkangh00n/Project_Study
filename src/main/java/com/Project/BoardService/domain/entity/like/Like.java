package com.Project.BoardService.domain.entity.like;

import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Like(User user, Post post){
        this.user = user;
        this.post = post;
        //양방향 매핑
        user.getLikes().add(this);
        post.getLikes().add(this);
    }

    public static Like createLike(User user, Post post){
        return Like.builder()
                .user(user)
                .post(post)
                .build();
    }

}
