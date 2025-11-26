package com.dgsw.javaassignmentproject.domain.post.controller;

import com.dgsw.javaassignmentproject.domain.post.dto.PostRequest;
import com.dgsw.javaassignmentproject.domain.post.dto.PostResponse;
import com.dgsw.javaassignmentproject.domain.post.dto.PostUpdateRequest;
import com.dgsw.javaassignmentproject.domain.post.service.PostService;
import com.dgsw.javaassignmentproject.global.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<PostResponse>> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(postService.getPost(id)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail("게시글을 찾을 수 없습니다!"));
        }
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
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok(ApiResponse.ok());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST, "삭제할 게시글을 찾을 수 없습니다!"));
        }
    }
}
