package com.Project.BoardService.controller.comment;

import com.Project.BoardService.domain.dto.commentDto.CommentResponseDto;
import com.Project.BoardService.domain.dto.commentDto.CommentSaveRequestDto;
import com.Project.BoardService.domain.dto.postDto.PostResponseDto;
import com.Project.BoardService.domain.user.User;
import com.Project.BoardService.jwt.LogIn;
import com.Project.BoardService.service.comment.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "댓글 REST API", description = "댓글에 대한 REST API")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 저장", description = "댓글 저장 API")
    @ApiResponse(responseCode = "201", description = "댓글 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
    @PostMapping("/posts/{id}/comments")
    public CommentResponseDto writeComment(@PathVariable("id") Long postId, @Validated @RequestBody CommentSaveRequestDto commentSaveRequestDto, @LogIn User user){
        return commentService.writeComment(user, postId, commentSaveRequestDto);
    }

}
