package com.Project.BoardService.domain.comment;

import com.Project.BoardService.domain.common.BaseTimeEntity;
import com.Project.BoardService.domain.post.Post;
import com.Project.BoardService.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String comment, User user, Post post){
        this.comment = comment;
        this.user = user;
        this.post = post;

        //양방향 매핑
        user.getComments().add(this);
        post.getComments().add(this);

    }
}
