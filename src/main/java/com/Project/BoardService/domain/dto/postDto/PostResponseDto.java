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

    @Schema(description = "좋아요")
    private Long likeCount;

    @Schema(description = "생성일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifiedDate;

    @Builder
    private PostResponseDto(Long id, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate, User user, List<CommentResponseDto> comments, Long likeCount){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.email = user.getEmail();
        this.commentList = comments;
        this.likeCount = likeCount;
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

    public static PostResponseDto fromDtoForFindById(Post post, List<CommentResponseDto> comments, Long LikeCount){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .user(post.getUser())
                .comments(comments)
                .likeCount(LikeCount)
                .build();
    }
}
