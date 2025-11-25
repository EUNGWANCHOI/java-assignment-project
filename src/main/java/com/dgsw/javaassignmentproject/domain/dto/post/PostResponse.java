package com.dgsw.javaassignmentproject.domain.dto.post;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author
) {}