package com.Project.BoardService.domain.dto.postDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Schema(description = "게시글 수정 DTO")
@Getter
public class PostUpdateRequestDto {

    @Schema(description = "수정 제목")
    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @Schema(description = "수정 내용")
    @NotEmpty
    @Size(min = 1, max = 1000)
    private String content;
}
