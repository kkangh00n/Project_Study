package com.Project.BoardService.domain.dto.likeDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponseDto {

    private String resultMessage;

    private LikeResponseDto(String resultMessage){
        this.resultMessage = resultMessage;
    }

    public static LikeResponseDto of(boolean result){
        if(result){
            return new LikeResponseDto("좋아요 추가");
        }
        else return new LikeResponseDto("좋아요 취소");
    }
}
