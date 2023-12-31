package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.dto.commentDto.CommentResponseDto;
import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.post.UploadFile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "특정 게시글 응답 DTO")
@Getter
@AllArgsConstructor
@Builder
public class FindByIdPostResponseDto {

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
    private Integer likeCount;

    @Schema(description = "생성일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifiedDate;

    @Schema(description = "저장된 이미지 이름")
    private List<String> imagesName;

    public static FindByIdPostResponseDto of(Post post, List<CommentResponseDto> comments){
        return FindByIdPostResponseDto.builder()
                .id(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .commentList(comments)
                .likeCount(post.getLikeCount())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .imagesName(post.getImages().stream().map(UploadFile::getUploadName).collect(Collectors.toList()))
                .build();
    }
}
