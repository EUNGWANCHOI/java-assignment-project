package com.dgsw.javaassignmentproject.service;

import com.dgsw.javaassignmentproject.domain.user.service.UserServiceImpl;
import com.dgsw.javaassignmentproject.global.exception.CustomException;
import com.dgsw.javaassignmentproject.global.exception.ErrorCode;
import com.dgsw.javaassignmentproject.domain.user.dto.UserResponse;
import com.dgsw.javaassignmentproject.domain.user.entity.User;
import com.dgsw.javaassignmentproject.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserServiceImpl userService = new UserServiceImpl(userRepository);

    @Test
    void getUser_success() {
        // given
        User mockUser = User.builder()
                .id(1L)
                .name("test")
                .age(20)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // when
        UserResponse response = userService.getUser(1L);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("test");
        assertThat(response.age()).isEqualTo(20);
    }

    @Test
    void getUser_fail_notFound() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> userService.getUser(1L))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.USER_NOT_FOUND.getMessage());
    }
}

