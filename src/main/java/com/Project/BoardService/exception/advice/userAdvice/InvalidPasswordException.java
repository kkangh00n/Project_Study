package com.Project.BoardService.exception.advice.userAdvice;

import com.Project.BoardService.exception.advice.BadRequestException;

public class InvalidPasswordException extends BadRequestException {

    private final static String MESSAGE = "비밀번호는 공백이 존재할 수 없습니다.";

    public InvalidPasswordException() {
        super(MESSAGE);
    }
}
