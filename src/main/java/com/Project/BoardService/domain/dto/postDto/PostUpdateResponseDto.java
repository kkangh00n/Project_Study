package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.dto.commentDto.CommentResponseDto;
import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "게시글 응답 DTO")
@Getter
public class PostUpdateResponseDto {

    @Schema(description = "게시글 식별자")
    private Long id;

    @Schema(description = "작성자 이메일")
    private String email;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "수정일자")
    private LocalDateTime modifiedDate;

    @Builder
    private PostUpdateResponseDto(Long id, String email, String title, String content, LocalDateTime modifiedDate){
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }

    //Entity -> DTO
    public static PostUpdateResponseDto of(Post post){
        return PostUpdateResponseDto.builder()
                .id(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
