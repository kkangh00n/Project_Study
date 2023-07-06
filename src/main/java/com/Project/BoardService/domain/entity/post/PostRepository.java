package com.Project.BoardService.domain.entity.post;

import com.Project.BoardService.domain.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    //전체 게시글 조회 기능
    @EntityGraph(attributePaths = {"user"})
    List<Post> findTop100AllByOrderByCreateDateDesc();

    @EntityGraph(attributePaths = {"user"})
    @Query("select p from Post p")
    Page<Post> findAllPosts(Pageable pageable);

    //특정 게시글 조회 기능
    @Override
    @EntityGraph(attributePaths = {"user"})
    Optional<Post> findById(Long id);

    //게시글 검색 기능
    @EntityGraph(attributePaths = {"user"})
    List<Post> findTop100ByTitleContainingOrderByCreateDateDesc(String title);

    Integer countPostsByUser(User user);
}
