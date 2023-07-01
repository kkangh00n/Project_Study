package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.dto.commentDto.CommentResponseDto;
import com.Project.BoardService.domain.post.Post;
import com.Project.BoardService.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "게시글 응답 DTO")
@Getter
public class PostResponseDto {

    @Schema(description = "게시글 식별자")
    private Long id;

    @Schema(description = "작성자 이메일")
    private String email;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "댓글")
    private List<CommentResponseDto> commentList;

    @Schema(description = "생성일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifiedDate;

    @Builder
    private PostResponseDto(Long id, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate, User user, List<CommentResponseDto> comments){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.email = user.getEmail();
        this.commentList = comments;
    }

    //Entity -> DTO
    public static PostResponseDto of(Post post){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .user(post.getUser())
                .build();
    }

    public static PostResponseDto changeBy(Post post, List<CommentResponseDto> comments){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .user(post.getUser())
                .comments(comments)
                .build();
    }
}
