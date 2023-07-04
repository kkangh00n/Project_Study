package com.Project.BoardService.service.comment;

import com.Project.BoardService.domain.entity.comment.Comment;
import com.Project.BoardService.domain.entity.comment.CommentRepository;
import com.Project.BoardService.domain.dto.commentDto.CommentResponseDto;
import com.Project.BoardService.domain.dto.commentDto.CommentSaveRequestDto;
import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.post.PostRepository;
import com.Project.BoardService.domain.entity.user.User;
import com.Project.BoardService.exception.advice.postAdvice.NotFoundPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

//    public List<CommentResponseDto> findCommentByPost(Post post){
//        return commentRepository.findCommentsByPost(post).stream().map(CommentResponseDto::of).collect(Collectors.toList());
//    }

    @Transactional
    public CommentResponseDto writeComment(User user, Long postId, CommentSaveRequestDto commentSaveRequestDto){
        Post findPost = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
        Comment saveComment = commentRepository.save(commentSaveRequestDto.toEntity(user, findPost));
        return CommentResponseDto.of(saveComment);
    }
}
