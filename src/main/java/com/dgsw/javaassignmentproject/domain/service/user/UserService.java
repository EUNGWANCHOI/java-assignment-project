package com.dgsw.javaassignmentproject.domain.service.user;

import com.dgsw.javaassignmentproject.domain.dto.user.UserRequest;
import com.dgsw.javaassignmentproject.domain.dto.user.UserResponse;
import com.dgsw.javaassignmentproject.domain.dto.user.UserUpdateRequest;

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
