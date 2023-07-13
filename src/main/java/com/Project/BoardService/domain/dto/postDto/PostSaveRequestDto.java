package com.Project.BoardService.domain.dto.postDto;

import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.post.UploadFile;
import com.Project.BoardService.domain.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema(description = "게시글 요청 DTO")
@Getter
public class PostSaveRequestDto {

    @Schema(description = "제목")
    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @Schema(description = "내용")
    @NotEmpty
    @Size(min = 1, max = 1000)
    private String content;

    @Schema(description = "이미지")
    @Size(min = 1, max = 10)
    private List<MultipartFile> images;

    @Builder
    public PostSaveRequestDto(String title, String content, List<MultipartFile> images){
        this.title = title;
        this.content = content;
        this.images = images;
    }

    // 엔티티 변환
    public Post toEntity(User user, List<UploadFile> images){
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .likeCount(0)
                .commentCount(0)
                .images(images)
                .build();
    }
}
