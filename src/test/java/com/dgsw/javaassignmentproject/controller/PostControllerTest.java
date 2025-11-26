package com.dgsw.javaassignmentproject.controller;

import com.dgsw.javaassignmentproject.domain.post.dto.PostRequest;
import com.dgsw.javaassignmentproject.domain.post.dto.PostUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPost_success() throws Exception {
        PostRequest request = new PostRequest("테스트 제목", "테스트 내용", "작성자");

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("테스트 제목"))
                .andExpect(jsonPath("$.data.author").value("작성자"));
    }

    @Test
    void getPost_fail_notFound() throws Exception {
        mockMvc.perform(get("/posts/{id}", 999))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void updatePost_success() throws Exception {
        PostRequest createRequest = new PostRequest("업데이트 전 제목", "업데이트 전 내용", "작성자");
        String content = mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long postId = objectMapper.readTree(content).path("data").path("id").asLong();
        PostUpdateRequest updateRequest = new PostUpdateRequest("업데이트 후 제목", "업데이트 후 내용", "작성자");

        mockMvc.perform(put("/posts/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("업데이트 후 제목"));
    }

    @Test
    void deletePost_fail_notFound() throws Exception {
        mockMvc.perform(delete("/posts/{id}", 123))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void searchPosts_success() throws Exception {
        PostRequest request1 = new PostRequest("검색 테스트1", "내용1", "작성자");
        PostRequest request2 = new PostRequest("검색 테스트2", "내용2", "작성자");
        mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/posts/search").param("keyword", "검색"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2));
    }
}
