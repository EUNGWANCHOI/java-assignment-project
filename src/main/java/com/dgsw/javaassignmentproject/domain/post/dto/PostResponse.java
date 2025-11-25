package com.dgsw.javaassignmentproject.domain.post.dto;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author
) {}