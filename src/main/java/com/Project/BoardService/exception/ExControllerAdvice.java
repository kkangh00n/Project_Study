package com.Project.BoardService.exception;

import com.Project.BoardService.exception.advice.BadRequestException;
import com.Project.BoardService.exception.advice.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResult NotFound(NotFoundException e) {
        return new ErrorResult(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResult BadRequest(BadRequestException e) {
        return new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    //경로변수 오류
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult typeMismatchPath(MethodArgumentTypeMismatchException e) {
        return new ErrorResult(HttpStatus.BAD_REQUEST.value(), "올바르지 않은 경로변수");
    }

    //@RequestBody 바인딩 오류
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult typeMismatchBinding(HttpMessageNotReadableException e) {
        return new ErrorResult(HttpStatus.BAD_REQUEST.value(), "올바르지 않은 필드 타입");
    }

    }
