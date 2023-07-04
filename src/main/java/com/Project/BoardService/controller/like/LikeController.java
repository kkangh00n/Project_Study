package com.Project.BoardService.controller.like;

import com.Project.BoardService.domain.dto.likeDto.LikeResponseDto;
import com.Project.BoardService.domain.dto.postDto.PostUpdateResponseDto;
import com.Project.BoardService.domain.entity.user.User;
import com.Project.BoardService.jwt.LogIn;
import com.Project.BoardService.service.like.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요 REST API", description = "좋아요에 대한 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "좋아요", description = "좋아요 기능 API")
    @ApiResponse(responseCode = "200", description = "좋아요 or 취소 성공", content = @Content(schema = @Schema(implementation = LikeResponseDto.class)))
    @PostMapping("/{id}/likes")
    @ResponseStatus(HttpStatus.OK)
    public LikeResponseDto LikeOrUnlike(@PathVariable("id") Long postId, @LogIn User user){
        boolean result = likeService.addOrCancel(postId, user);
        return LikeResponseDto.of(result);
    }


}
