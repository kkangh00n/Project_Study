package com.Project.BoardService.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LogInResponse {

    private String accessToken;

    @Builder
    public LogInResponse(String accessToken){
        this.accessToken = accessToken;
    }

}
