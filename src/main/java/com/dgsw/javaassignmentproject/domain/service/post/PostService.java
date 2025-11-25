package com.dgsw.javaassignmentproject.domain.service.post;

import com.dgsw.javaassignmentproject.domain.dto.post.PostRequest;
import com.dgsw.javaassignmentproject.domain.dto.post.PostResponse;
import com.dgsw.javaassignmentproject.domain.dto.post.PostUpdateRequest;

import java.util.List;

public interface PostService {

    PostResponse createPost(PostRequest request);

    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();

    List<PostResponse> searchPosts(String keyword);

    PostResponse updatePost(Long id, PostUpdateRequest request);

    void deletePost(Long id);
}