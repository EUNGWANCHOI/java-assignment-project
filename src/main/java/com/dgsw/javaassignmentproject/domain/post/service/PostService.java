package com.dgsw.javaassignmentproject.domain.post.service;

import com.dgsw.javaassignmentproject.domain.post.dto.PostRequest;
import com.dgsw.javaassignmentproject.domain.post.dto.PostResponse;
import com.dgsw.javaassignmentproject.domain.post.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {

    PostResponse createPost(PostRequest request);

    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();

    List<PostResponse> searchPosts(String keyword);

    PostResponse updatePost(Long id, PostUpdateRequest request);

    void deletePost(Long id);
}