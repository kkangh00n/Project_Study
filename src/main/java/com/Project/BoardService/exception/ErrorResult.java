package com.Project.BoardService.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "예외 시 응답 객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

    @Schema(description = "상태 코드")
    Integer code;

    @Schema(description = "오류 메시지")
    String message;

}
