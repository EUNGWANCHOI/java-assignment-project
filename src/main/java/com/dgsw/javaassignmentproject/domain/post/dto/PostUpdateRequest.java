package com.dgsw.javaassignmentproject.domain.post.dto;

public record PostUpdateRequest(
        String title,
        String content,
        String author
) {}