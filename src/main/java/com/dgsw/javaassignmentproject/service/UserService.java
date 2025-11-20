package com.dgsw.javaassignmentproject.service;

import com.dgsw.javaassignmentproject.dto.user.UserRequest;
import com.dgsw.javaassignmentproject.dto.user.UserResponse;
import com.dgsw.javaassignmentproject.dto.user.UserUpdateRequest;

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
