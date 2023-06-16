package com.Project.BoardService.domain;

import com.Project.BoardService.domain.common.BaseTimeEntity;
import com.Project.BoardService.domain.dto.PostUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

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
    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto){
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
    }

}
