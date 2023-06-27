package com.Project.BoardService.controller.user;

import com.Project.BoardService.config.security.LogInResponse;
import com.Project.BoardService.domain.dto.userDto.LogInRequestDto;
import com.Project.BoardService.domain.dto.userDto.UserResponseDto;
import com.Project.BoardService.domain.dto.userDto.SignInRequestDto;
import com.Project.BoardService.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 REST API", description = "회원들에 대한 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 가입", description = "회원 가입 API")
    @ApiResponse(responseCode = "201", description = "회원 가입 성공", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto signIn(@Validated @RequestBody SignInRequestDto signInRequestDto){
        return userService.signIn(signInRequestDto);
    }

    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/logIn")
    public LogInResponse logIn(@Validated @RequestBody LogInRequestDto logInRequestDto){
        return userService.logIn(logInRequestDto);
    }

}
