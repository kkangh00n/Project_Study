package com.Project.BoardService.exception;

import com.Project.BoardService.exception.advice.NotFoundException;
import com.Project.BoardService.exception.postAdvice.NotFoundPostException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResult badRequestEx(NotFoundException e){
        return new ErrorResult(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

}
