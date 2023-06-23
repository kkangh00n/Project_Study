package com.Project.BoardService.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "유효성 예외 시 응답 객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidErrorResult {
    @Schema(description = "상태 코드")
    Integer code;

    @Schema(description = "오류 메시지")
    String message;

    @Schema(description = "필드 에러 리스트")
    List<Error> errors;

    @Schema(description = "필드 에러")
    @Data
    @AllArgsConstructor
    public static class Error{
        @Schema(description = "필드 이름")
        String field;

        @Schema(description = "입력한 값")
        String value;

        @Schema(description = "오류 코드")
        String code;

        @Schema(description = "출력할 오류 메시지")
        String message;
    }
}
