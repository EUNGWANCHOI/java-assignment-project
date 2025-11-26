package com.dgsw.javaassignmentproject.domain.auth.service;

import com.dgsw.javaassignmentproject.domain.auth.repository.RefreshTokenRepository;
import com.dgsw.javaassignmentproject.domain.user.entity.User;
import com.dgsw.javaassignmentproject.domain.user.repository.UserRepository;
import com.dgsw.javaassignmentproject.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public User signUp(String name, int age, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .name(name)
                .age(age)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        return userRepository.save(user);
    }

    public LoginResponse signIn(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(email);
        String refreshToken = jwtUtil.createRefreshToken(email);

        refreshTokenRepository.save(email, refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }

    public String reissueAccessToken(String refreshToken) {
        String email = refreshTokenRepository.findByEmail(refreshToken);
        if (email == null) {
            throw new RuntimeException("RefreshToken이 유효하지 않습니다.");
        }

        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("RefreshToken이 만료되었거나 유효하지 않습니다.");
        }

        return jwtUtil.createAccessToken(email);
    }

    public static record LoginResponse(String accessToken, String refreshToken) {}
}
