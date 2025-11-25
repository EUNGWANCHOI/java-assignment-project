package com.dgsw.javaassignmentproject.controller;

import com.dgsw.javaassignmentproject.common.api.ApiResponse;
import com.dgsw.javaassignmentproject.dto.post.PostRequest;
import com.dgsw.javaassignmentproject.dto.post.PostResponse;
import com.dgsw.javaassignmentproject.dto.post.PostUpdateRequest;
import com.dgsw.javaassignmentproject.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> create(@Valid @RequestBody PostRequest request) {
        return ApiResponse.created(postService.createPost(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(postService.getPost(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<PostResponse>> search(@RequestParam String keyword) {
        return ApiResponse.ok(postService.searchPosts(keyword));
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getAll() {
        return ApiResponse.ok(postService.getAllPosts());
    }

    @PutMapping("/{id}")
    public ApiResponse<PostResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequest request
    ) {
        return ApiResponse.ok(postService.updatePost(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ApiResponse.ok();
    }
}