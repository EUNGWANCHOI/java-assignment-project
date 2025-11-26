package com.dgsw.javaassignmentproject.domain.auth.dto;

public record SignupRequest(
        String name,
        int age,
        String email,
        String password
) {
}
