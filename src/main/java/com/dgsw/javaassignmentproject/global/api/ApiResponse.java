package com.dgsw.javaassignmentproject.global.api;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        boolean success,
        T data,
        ErrorResponse error
) {

    // 성공 응답
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // 생성 응답
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // 빈 성공 응답
    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(true, null, null);
    }

    // 실패 응답 (에러 코드 + 메시지)
    public static ApiResponse<Void> error(ErrorResponse error) {
        return new ApiResponse<>(false, null, error);
    }

    // 실패 응답 (에러코드, 상태만 전달하고 싶을 때)
    public static ApiResponse<Void> error(HttpStatus status, String message) {
        return new ApiResponse<>(false, null, new ErrorResponse(status.value(), message));
    }
}
