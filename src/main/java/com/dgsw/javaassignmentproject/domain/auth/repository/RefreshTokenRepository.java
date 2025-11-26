package com.dgsw.javaassignmentproject.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public void save (String id, String refreshToken) {
        redisTemplate.opsForValue().set(id, refreshToken);
    }

    public void delete(String email) {
        redisTemplate.delete(email);
    }

    public String findByEmail(String email) {
        return redisTemplate.opsForValue().get(email);
    }
}