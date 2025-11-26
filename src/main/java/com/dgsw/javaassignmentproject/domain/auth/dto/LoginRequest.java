package com.dgsw.javaassignmentproject.domain.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}