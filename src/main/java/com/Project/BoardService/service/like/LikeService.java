package com.Project.BoardService.service.like;

import com.Project.BoardService.domain.entity.like.Like;
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
            return false;
        }
        else{
            Like like = Like.createLike(user, findPost);
            likeRepository.save(like);
            return true;
        }
    }
}
