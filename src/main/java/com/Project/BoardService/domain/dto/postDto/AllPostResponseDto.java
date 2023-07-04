package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.entity.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "전체 게시글 조회 응답 DTO")
@Getter
@AllArgsConstructor
@Builder
public class AllPostResponseDto {

    @Schema(description = "게시글 식별자")
    private Long id;

    @Schema(description = "작성자 이메일")
    private String email;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "댓글 수")
    private Integer commentCount;

    @Schema(description = "좋아요 수")
    private Integer likeCount;

    @Schema(description = "수정일자")
    private LocalDateTime modifiedDate;

    public static AllPostResponseDto of(Post post){
        return AllPostResponseDto.builder()
                .id(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .commentCount(post.getComments().size())
                .likeCount(post.getPostLikes().size())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
