package com.Project.BoardService.domain.dto.userDto;

import com.Project.BoardService.domain.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "회원 가입 DTO")
@Getter
public class SignInRequestDto {

    @Schema(description = "이메일")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank
    @Size(min = 8, max = 15)
    private String password;

    @Builder
    public SignInRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}
