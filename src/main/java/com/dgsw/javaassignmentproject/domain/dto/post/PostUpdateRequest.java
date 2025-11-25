package com.dgsw.javaassignmentproject.domain.dto.post;

public record PostUpdateRequest(
        String title,
        String content,
        String author
) {}