package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.post.UploadFile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "게시글 저장에 대한 응답 DTO")
@Getter
public class PostSaveResponseDto {

    @Schema(description = "게시글 식별자")
    private Long id;

    @Schema(description = "작성자 이메일")
    private String email;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "생성일자")
    private LocalDateTime createDate;

    @Schema(description = "저장된 이미지 이름")
    private List<String> imagesName;

    @Builder
    public PostSaveResponseDto(Long id, String email, String title, String content, LocalDateTime createDate, List<String> imagesName){
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.imagesName = imagesName;
    }

    public static PostSaveResponseDto of(Post post) {
        return PostSaveResponseDto.builder()
                .id(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .imagesName(post.getImages().stream().map(UploadFile::getUploadName).collect(Collectors.toList()))
                .build();
    }
}
