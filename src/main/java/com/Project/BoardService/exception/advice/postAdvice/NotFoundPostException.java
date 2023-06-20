package com.Project.BoardService.exception.advice.postAdvice;

import com.Project.BoardService.exception.advice.NotFoundException;

public class NotFoundPostException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 게시물입니다";
    public NotFoundPostException() {
        super(MESSAGE);
    }
}
