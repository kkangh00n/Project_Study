package com.Project.BoardService.domain.dto;

import com.Project.BoardService.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {

    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 1000)
    private String content;

    @Builder
    public PostSaveRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    // 엔티티 변환
    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
