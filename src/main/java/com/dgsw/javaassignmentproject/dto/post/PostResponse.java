package com.dgsw.javaassignmentproject.dto.post;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author
) {}