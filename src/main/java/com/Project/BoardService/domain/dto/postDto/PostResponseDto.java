package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "게시글 응답 DTO")
@Getter
public class PostResponseDto {

    @Schema(description = "게시글 식별자")
    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "생성일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifiedDate;

    @Builder
    private PostResponseDto(Long id, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    //Entity -> DTO
    public static PostResponseDto of (Post post){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
