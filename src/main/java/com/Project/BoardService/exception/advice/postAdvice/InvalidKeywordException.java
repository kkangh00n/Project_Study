package com.Project.BoardService.exception.advice.postAdvice;

import com.Project.BoardService.exception.advice.BadRequestException;

public class InvalidKeywordException extends BadRequestException {
    private static final String MESSAGE = "검색 키워드는 1글자 이상입니다";
    public InvalidKeywordException() {
        super(MESSAGE);
    }
}
