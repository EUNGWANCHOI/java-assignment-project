package com.dgsw.javaassignmentproject.repository;

import java.util.List;
import com.dgsw.javaassignmentproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyword);
}