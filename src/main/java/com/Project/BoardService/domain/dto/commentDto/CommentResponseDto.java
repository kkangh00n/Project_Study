package com.Project.BoardService.domain.dto.commentDto;

import com.Project.BoardService.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private String comment;

    private LocalDateTime createdDate;

    @Builder
    private CommentResponseDto(String comment, LocalDateTime createdDate){
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public static CommentResponseDto of(Comment comment){
        return CommentResponseDto.builder()
                .comment(comment.getComment())
                .createdDate(comment.getCreateDate())
                .build();
    }
}
