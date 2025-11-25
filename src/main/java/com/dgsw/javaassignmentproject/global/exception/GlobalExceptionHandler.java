package com.dgsw.javaassignmentproject.global.exception;

import com.dgsw.javaassignmentproject.global.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.error(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(CustomException.class)
    public ApiResponse<?> handleCustom(CustomException e) {
        return ApiResponse.error(e.getErrorCode().getStatus(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleUnknown(Exception e) {
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다.");
    }
}
