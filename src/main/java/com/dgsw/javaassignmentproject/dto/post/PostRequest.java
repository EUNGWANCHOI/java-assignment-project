package com.dgsw.javaassignmentproject.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        String title,

        @NotBlank(message = "내용은 비어 있을 수 없습니다.")
        String content,

        @NotBlank(message = "작성자는 비어 있을 수 없습니다.")
        String author
) {}