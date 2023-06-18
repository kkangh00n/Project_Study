package com.Project.BoardService.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {

    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 1000)
    private String content;
}
