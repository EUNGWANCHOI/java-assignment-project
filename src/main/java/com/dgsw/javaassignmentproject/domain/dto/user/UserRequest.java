package com.dgsw.javaassignmentproject.domain.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank(message = "이름은 비어 있을 수 없습니다.")
        String name,

        @Min(value = 1, message = "나이는 최소 1살 이상이어야 합니다.")
        int age
) {}
