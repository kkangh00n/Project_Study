package com.Project.BoardService.exception;

import com.Project.BoardService.exception.advice.NotFoundException;
import com.Project.BoardService.exception.advice.UnauthorizedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class UserExControllerAdvice {

    //인증 실패 시
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResult NotFound(UnauthorizedException e) {
        return new ErrorResult(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
