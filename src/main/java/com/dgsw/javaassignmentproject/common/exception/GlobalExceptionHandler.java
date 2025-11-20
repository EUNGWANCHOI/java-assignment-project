package com.dgsw.javaassignmentproject.common.exception;

import com.dgsw.javaassignmentproject.common.api.ApiResponse;
import com.dgsw.javaassignmentproject.common.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustom(CustomException ex) {
        var code = ex.getErrorCode();

        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.error(
                        new ErrorResponse(code.getStatus().value(), code.getMessage())
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknown(Exception ex) {

        return ResponseEntity
                .status(500)
                .body(ApiResponse.error(
                        new ErrorResponse(500, "알 수 없는 오류가 발생했습니다.")
                ));
    }
}
