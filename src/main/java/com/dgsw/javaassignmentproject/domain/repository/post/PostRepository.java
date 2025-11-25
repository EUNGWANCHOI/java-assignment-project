package com.dgsw.javaassignmentproject.domain.repository.post;

import java.util.List;
import com.dgsw.javaassignmentproject.domain.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyword);
}