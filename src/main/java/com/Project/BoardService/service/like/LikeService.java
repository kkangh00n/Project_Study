package com.Project.BoardService.service.like;

import com.Project.BoardService.domain.entity.like.PostLike;
import com.Project.BoardService.domain.entity.like.LikeRepository;
import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.post.PostRepository;
import com.Project.BoardService.domain.entity.user.User;
import com.Project.BoardService.exception.advice.postAdvice.NotFoundPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public boolean addOrCancel(Long postId, User user){

        Post findPost = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        if(likeRepository.existsLikeByUserAndPost(user, findPost)){
            likeRepository.deleteLikeByUserAndPost(user, findPost);
            findPost.increaseOrDecreaseLike(false);
            return false;
        }
        else{
            PostLike postLike = PostLike.createLike(user, findPost);
            likeRepository.save(postLike);
            findPost.increaseOrDecreaseLike(true);
            return true;
        }
    }
}
