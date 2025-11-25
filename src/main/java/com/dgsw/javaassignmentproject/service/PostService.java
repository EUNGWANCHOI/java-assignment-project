package com.dgsw.javaassignmentproject.service;

import com.dgsw.javaassignmentproject.dto.post.PostRequest;
import com.dgsw.javaassignmentproject.dto.post.PostResponse;
import com.dgsw.javaassignmentproject.dto.post.PostUpdateRequest;

import java.util.List;

public interface PostService {

    PostResponse createPost(PostRequest request);

    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();

    List<PostResponse> searchPosts(String keyword);

    PostResponse updatePost(Long id, PostUpdateRequest request);

    void deletePost(Long id);
}