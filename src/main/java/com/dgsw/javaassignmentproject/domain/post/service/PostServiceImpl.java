package com.dgsw.javaassignmentproject.domain.post.service;

import com.dgsw.javaassignmentproject.domain.post.dto.PostRequest;
import com.dgsw.javaassignmentproject.domain.post.dto.PostResponse;
import com.dgsw.javaassignmentproject.domain.post.dto.PostUpdateRequest;
import com.dgsw.javaassignmentproject.domain.post.entity.Post;
import com.dgsw.javaassignmentproject.domain.post.repository.PostRepository;
import com.dgsw.javaassignmentproject.global.exception.CustomException;
import com.dgsw.javaassignmentproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostResponse createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .author(request.author())
                .build();

        Post saved = postRepository.save(post);
        return toResponse(saved);
    }

    @Override
    public PostResponse getPost(Long id) {
        return toResponse(getPostOrThrow(id));
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<PostResponse> searchPosts(String keyword) {
        return postRepository.findByTitleContaining(keyword)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Post post = getPostOrThrow(id);

        if (request.title() != null) post.setTitle(request.title());
        if (request.content() != null) post.setContent(request.content());
        if (request.author() != null) post.setAuthor(request.author());

        return toResponse(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        Post post = getPostOrThrow(id);
        postRepository.delete(post);
    }

    private Post getPostOrThrow(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REQUEST));
    }

    private PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
    }
}