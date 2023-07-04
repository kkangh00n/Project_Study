package com.Project.BoardService.domain.entity.user;

import com.Project.BoardService.domain.entity.comment.Comment;
import com.Project.BoardService.domain.common.BaseTimeEntity;
import com.Project.BoardService.domain.entity.like.Like;
import com.Project.BoardService.domain.entity.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

}
