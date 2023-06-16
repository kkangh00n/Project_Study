package com.Project.BoardService.repository;

import com.Project.BoardService.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
