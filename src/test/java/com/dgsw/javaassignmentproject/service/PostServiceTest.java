package com.dgsw.javaassignmentproject.service;

import com.dgsw.javaassignmentproject.domain.post.dto.PostRequest;
import com.dgsw.javaassignmentproject.domain.post.dto.PostUpdateRequest;
import com.dgsw.javaassignmentproject.domain.post.entity.Post;
import com.dgsw.javaassignmentproject.domain.post.repository.PostRepository;
import com.dgsw.javaassignmentproject.domain.post.service.PostServiceImpl;
import com.dgsw.javaassignmentproject.global.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    void createPost_success() {
        PostRequest request = new PostRequest("제목1", "내용1", "작성자1");
        var response = postService.createPost(request);

        assertThat(response.title()).isEqualTo("제목1");
        assertThat(response.content()).isEqualTo("내용1");
        assertThat(response.author()).isEqualTo("작성자1");
    }

    @Test
    void getPost_success() {
        Post saved = postRepository.save(new Post(null, "조회제목", "조회내용", "작성자"));
        var response = postService.getPost(saved.getId());

        assertThat(response.title()).isEqualTo("조회제목");
        assertThat(response.content()).isEqualTo("조회내용");
    }

    @Test
    void getPost_fail_notFound() {
        assertThrows(CustomException.class, () -> postService.getPost(999L));
    }

    @Test
    void updatePost_success() {
        Post saved = postRepository.save(new Post(null, "업데이트전", "내용전", "작성자전"));
        PostUpdateRequest updateRequest = new PostUpdateRequest("업데이트후", "내용후", "작성자후");

        var updated = postService.updatePost(saved.getId(), updateRequest);
        assertThat(updated.title()).isEqualTo("업데이트후");
        assertThat(updated.content()).isEqualTo("내용후");
        assertThat(updated.author()).isEqualTo("작성자후");
    }

    @Test
    void deletePost_fail_notFound() {
        assertThrows(CustomException.class, () -> postService.deletePost(123L));
    }

    @Test
    void searchPosts_success() {
        postRepository.save(new Post(null,"Spring 테스트", "내용1", "작성자"));
        postRepository.save(new Post(null, "JUnit 테스트", "내용2", "작성자"));

        List<?> results = postService.searchPosts("테스트");
        assertThat(results.size()).isEqualTo(2);
    }
}
