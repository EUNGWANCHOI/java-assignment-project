package com.dgsw.javaassignmentproject.domain.user.service;

import com.dgsw.javaassignmentproject.domain.user.dto.UserRequest;
import com.dgsw.javaassignmentproject.domain.user.dto.UserResponse;
import com.dgsw.javaassignmentproject.domain.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUser(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest request);      // PUT

    UserResponse updateUserPartially(Long id, UserUpdateRequest request); // PATCH

    void deleteUser(Long id);

    List<UserResponse> searchUsers(String keyword);

}
