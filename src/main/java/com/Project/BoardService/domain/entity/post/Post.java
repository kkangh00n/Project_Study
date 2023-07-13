package com.Project.BoardService.domain.entity.post;

import com.Project.BoardService.domain.entity.comment.Comment;
import com.Project.BoardService.domain.common.BaseTimeEntity;
import com.Project.BoardService.domain.dto.postDto.PostUpdateRequestDto;
import com.Project.BoardService.domain.entity.like.PostLike;
import com.Project.BoardService.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private Integer commentCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostLike> postLikes = new HashSet<>();

    private Integer likeCount;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "IMAGES", joinColumns = @JoinColumn(name = "IMAGE_ID"))
    private List<UploadFile> images;

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
    public Post(String title, String content, User user, Integer commentCount, Integer likeCount, List<UploadFile> images){
        this.title = title;
        this.content = content;
        this.user = user;
        //양방향 매핑
        user.getPosts().add(this);

        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.images = images;
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto){
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
    }

    public void increaseComment(){
        this.commentCount++;
    }

    public void increaseOrDecreaseLike(boolean result){
        if(result){
            this.likeCount++;
        }
        else{
            this.likeCount--;
        }
    }

}
