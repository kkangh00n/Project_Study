package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "게시글 요청 DTO")
@Getter
public class PostSaveRequestDto {

    @Schema(description = "제목")
    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @Schema(description = "내용")
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
