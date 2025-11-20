package com.dgsw.javaassignmentproject.controller;

import com.dgsw.javaassignmentproject.common.api.ApiResponse;
import com.dgsw.javaassignmentproject.dto.user.UserRequest;
import com.dgsw.javaassignmentproject.dto.user.UserResponse;
import com.dgsw.javaassignmentproject.dto.user.UserUpdateRequest;
import com.dgsw.javaassignmentproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserRequest request) {
        return ApiResponse.created(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(userService.getUser(id));
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ) {
        return ApiResponse.ok(userService.updateUser(id, request));
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> partialUpdate(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
    ) {
        return ApiResponse.ok(userService.updateUserPartially(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.ok();
    }

    @GetMapping("/search")
    public ApiResponse<List<UserResponse>> search(@RequestParam String keyword) {
        return ApiResponse.ok(userService.searchUsers(keyword));
    }

}
