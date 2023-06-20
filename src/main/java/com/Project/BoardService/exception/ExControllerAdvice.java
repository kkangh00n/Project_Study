package com.Project.BoardService.exception;

import com.Project.BoardService.exception.advice.BadRequestException;
import com.Project.BoardService.exception.advice.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResult NotFound(NotFoundException e){
        return new ErrorResult(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResult BadRequest(BadRequestException e){
        return new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
