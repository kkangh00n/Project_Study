package com.Project.BoardService.repository;

import com.Project.BoardService.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //게시글 전체 조회 기능
    List<Post> findTop100AllByOrderByCreateDateDesc();
}
