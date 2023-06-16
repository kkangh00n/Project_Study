package com.Project.BoardService.domain.dto;

import com.Project.BoardService.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    private PostResponseDto(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    //Entity -> DTO
    public static PostResponseDto of (Post post){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
