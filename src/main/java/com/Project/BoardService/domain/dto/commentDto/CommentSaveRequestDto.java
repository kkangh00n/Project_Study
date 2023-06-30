package com.Project.BoardService.domain.dto.commentDto;

import com.Project.BoardService.domain.comment.Comment;
import com.Project.BoardService.domain.post.Post;
import com.Project.BoardService.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Schema(description = "댓글 요청 DTO")
@Getter
public class CommentSaveRequestDto {

    @Schema(description = "댓글 내용")
    @NotBlank
    private String comment;

    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .comment(comment)
                .user(user)
                .post(post)
                .build();
    }

}
