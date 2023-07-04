package com.Project.BoardService.domain.entity.like;

import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);

    boolean existsLikeByUserAndPost(User user, Post post);

    void deleteLikeByUserAndPost(User user, Post post);

    Optional<Long> countLikeByPost(Post post);
}
