package com.Project.BoardService.domain.dto.userDto;

import com.Project.BoardService.domain.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "비밀번호를 제외한 회원 응답 DTO")
@Getter
@Builder
public class UserInfoResponseDto {

    @Schema(description = "회원 식별자")
    private Long id;

    @Schema(description = "회원 이메일")
    private String email;

    @Schema(description = "작성 게시글 수")
    private Integer postCount;

    @Schema(description = "회원 가입일자")
    private LocalDateTime createDate;

    public static UserInfoResponseDto of(User user, Integer postCount){
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .postCount(postCount)
                .createDate(user.getCreateDate())
                .build();
    }

}
