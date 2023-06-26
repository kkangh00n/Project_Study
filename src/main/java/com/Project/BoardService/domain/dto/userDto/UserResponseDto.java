package com.Project.BoardService.domain.dto.userDto;

import com.Project.BoardService.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "회원 응답 DTO")
@Getter
@Builder
public class UserResponseDto {

    @Schema(description = "회원 식별자")
    private Long id;

    @Schema(description = "회원 이메일")
    private String email;

    @Schema(description = "회원 비밀번호")
    private String password;

    @Schema(description = "회원 가입일자")
    private LocalDateTime createDate;

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .createDate(user.getCreateDate())
                .build();
    }
}
