package com.Project.BoardService.domain.post;

import com.Project.BoardService.domain.comment.Comment;
import com.Project.BoardService.domain.common.BaseTimeEntity;
import com.Project.BoardService.domain.dto.postDto.PostUpdateRequestDto;
import com.Project.BoardService.domain.user.User;
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
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

//    //Builder 패턴 적용
//    private Post(Builder builder){
//        this.title = builder.title;
//        this.content = builder.content;
//    }
//
//    @NoArgsConstructor
//    public static class Builder{
//        private String title;
//        private String content;
//
//        public Builder title(String title){
//            this.title = title;
//            return this;
//        }
//        public Builder content(String content){
//            this.content = content;
//            return this;
//        }
//        public Post build(){
//            return new Post(this);
//        }
//    }

    @Builder
    public Post(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
        //양방향 매핑
        user.getPosts().add(this);
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto){
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
    }

}
