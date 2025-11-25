package com.dgsw.javaassignmentproject.dto.post;

public record PostUpdateRequest(
        String title,
        String content,
        String author
) {}