package com.dgsw.javaassignmentproject.domain.auth.controller;

import com.dgsw.javaassignmentproject.domain.auth.service.AuthService;
import com.dgsw.javaassignmentproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String email,
            @RequestParam String password
    ) {
        User user = authService.signUp(name, age, email, password);
        return ResponseEntity.ok(user);
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<AuthService.LoginResponse> signIn(
            @RequestParam String email,
            @RequestParam String password
    ) {
        AuthService.LoginResponse loginResponse = authService.signIn(email, password);
        return ResponseEntity.ok(loginResponse);
    }

    // AccessToken 재발급
    @PostMapping("/reissue")
    public ResponseEntity<String> reissueAccessToken(
            @RequestParam String refreshToken
    ) {
        String newAccessToken = authService.reissueAccessToken(refreshToken);
        return ResponseEntity.ok(newAccessToken);
    }
}
