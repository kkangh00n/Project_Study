package com.Project.BoardService.domain.dto.userDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Schema(description = "로그인 DTO")
@Getter
public class LogInRequestDto {

    @Schema(description = "이메일")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank
    private String password;
}
