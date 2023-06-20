package com.Project.BoardService.exception.postAdvice;

import com.Project.BoardService.exception.advice.NotFoundException;

public class NotFoundPostException extends NotFoundException {
    private final static String MESSAGE = "존재하지 않는 게시물입니다";
    public NotFoundPostException() {
        super(MESSAGE);
    }
}
