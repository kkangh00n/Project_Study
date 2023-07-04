package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.entity.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "게시글 저장에 대한 응답 DTO")
@Getter
public class PostSaveResponseDto {

    @Schema(description = "게시글 식별자")
    private Long id;

    @Schema(description = "작성자 이메일")
    private String email;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "생성일자")
    private LocalDateTime createDate;

    @Builder
    public PostSaveResponseDto(Long id, String email, String title, String content, LocalDateTime createDate){
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public static PostSaveResponseDto of(Post post) {
        return PostSaveResponseDto.builder()
                .id(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .build();
    }
}
