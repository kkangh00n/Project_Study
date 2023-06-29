package com.Project.BoardService.domain.post;

import com.Project.BoardService.domain.post.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    //게시글 전체 조회 기능
    @EntityGraph(attributePaths = {"user"})
    List<Post> findTop100AllByOrderByCreateDateDesc();

    @Override
    @EntityGraph(attributePaths = {"user"})
    Optional<Post> findById(Long id);

    //게시글 검색 기능
    List<Post> findTop100ByTitleContainingOrderByCreateDateDesc(String title);
}
