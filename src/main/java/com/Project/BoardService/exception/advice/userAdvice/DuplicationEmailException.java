package com.Project.BoardService.exception.advice.userAdvice;

import com.Project.BoardService.exception.advice.BadRequestException;

public class DuplicationEmailException extends BadRequestException {

    private final static String MESSAGE = "중복된 이메일이 존재합니다.";
    public DuplicationEmailException() {
        super(MESSAGE);
    }
}
