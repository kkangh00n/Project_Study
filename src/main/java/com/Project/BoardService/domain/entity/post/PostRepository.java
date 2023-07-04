package com.Project.BoardService.domain.entity.post;

import com.Project.BoardService.domain.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    //전체 게시글 조회 기능
    @EntityGraph(attributePaths = {"user", "comments", "postLikes"})
    List<Post> findTop100AllByOrderByCreateDateDesc();

    //특정 게시글 조회 기능
    @Override
    @EntityGraph(attributePaths = {"user"})
    Optional<Post> findById(Long id);

    //게시글 검색 기능
    @EntityGraph(attributePaths = {"user", "comments", "postLikes"})
    List<Post> findTop100ByTitleContainingOrderByCreateDateDesc(String title);

    Integer countPostsByUser(User user);
}
