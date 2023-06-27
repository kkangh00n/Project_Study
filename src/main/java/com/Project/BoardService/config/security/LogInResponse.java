package com.Project.BoardService.config.security;

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
