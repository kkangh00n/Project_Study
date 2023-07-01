package com.Project.BoardService.domain.dto.commentDto;

import com.Project.BoardService.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private String email;

    private String comment;

    private LocalDateTime createdDate;

    @Builder
    private CommentResponseDto(String email, String comment, LocalDateTime createdDate){
        this.email = email;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public static CommentResponseDto of(Comment comment){
        return CommentResponseDto.builder()
                .email(comment.getUser().getEmail())
                .comment(comment.getComment())
                .createdDate(comment.getCreateDate())
                .build();
    }
}
