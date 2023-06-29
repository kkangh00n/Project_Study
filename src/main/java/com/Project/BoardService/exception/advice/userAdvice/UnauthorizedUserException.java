package com.Project.BoardService.exception.advice.userAdvice;

import com.Project.BoardService.exception.advice.UnauthorizedException;

public class UnauthorizedUserException extends UnauthorizedException {

    private final static String MESSAGE = "접근자격이 없습니다.";
    public UnauthorizedUserException() {
        super(MESSAGE);
    }
}
