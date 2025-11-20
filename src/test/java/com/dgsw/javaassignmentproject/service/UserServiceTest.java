package com.dgsw.javaassignmentproject.service;

import com.dgsw.javaassignmentproject.common.exception.CustomException;
import com.dgsw.javaassignmentproject.common.exception.ErrorCode;
import com.dgsw.javaassignmentproject.dto.user.UserRequest;
import com.dgsw.javaassignmentproject.dto.user.UserResponse;
import com.dgsw.javaassignmentproject.entity.User;
import com.dgsw.javaassignmentproject.repository.UserRepository;
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

