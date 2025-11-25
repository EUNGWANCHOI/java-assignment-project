package com.dgsw.javaassignmentproject.domain.user.service;

import com.dgsw.javaassignmentproject.global.exception.CustomException;
import com.dgsw.javaassignmentproject.global.exception.ErrorCode;
import com.dgsw.javaassignmentproject.domain.user.dto.UserRequest;
import com.dgsw.javaassignmentproject.domain.user.dto.UserResponse;
import com.dgsw.javaassignmentproject.domain.user.dto.UserUpdateRequest;
import com.dgsw.javaassignmentproject.domain.user.entity.User;
import com.dgsw.javaassignmentproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .name(request.name())
                .age(request.age())
                .build();

        User saved = userRepository.save(user);

        return toResponse(saved);
    }

    @Override
    public UserResponse getUser(Long id) {
        return toResponse(getUserOrThrow(id));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = getUserOrThrow(id);

        user.setName(request.name());
        user.setAge(request.age());

        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserPartially(Long id, UserUpdateRequest request) {
        User user = getUserOrThrow(id);

        if (request.name() != null) user.setName(request.name());
        if (request.age() != null) user.setAge(request.age());

        return toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserOrThrow(id);
        userRepository.delete(user);
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getAge());
    }
    @Override
    public List<UserResponse> searchUsers(String keyword) {
        return userRepository.findByNameContaining(keyword)
                .stream()
                .map(this::toResponse)
                .toList();
    }

}
