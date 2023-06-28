package com.Project.BoardService.jwt;

import com.Project.BoardService.exception.ErrorResult;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResult JWTException(JwtException e){
        return new ErrorResult(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
