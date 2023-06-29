package com.Project.BoardService.domain.comment;

import com.Project.BoardService.domain.post.Post;
import com.Project.BoardService.domain.user.User;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
