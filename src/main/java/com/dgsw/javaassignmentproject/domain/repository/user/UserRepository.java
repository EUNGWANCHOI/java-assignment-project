package com.dgsw.javaassignmentproject.domain.repository.user;

import com.dgsw.javaassignmentproject.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String keyword);
}
