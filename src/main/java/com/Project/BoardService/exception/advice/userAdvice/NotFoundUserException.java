package com.Project.BoardService.exception.advice.userAdvice;

import com.Project.BoardService.exception.advice.NotFoundException;

public class NotFoundUserException extends NotFoundException {

    private static final String MESSAGE = "아이디 또는 비밀번호가 일치하지 않습니다.";
    public NotFoundUserException() {
        super(MESSAGE);
    }
}
