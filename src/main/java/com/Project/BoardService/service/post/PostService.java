package com.Project.BoardService.service.post;

import com.Project.BoardService.domain.dto.postDto.*;
import com.Project.BoardService.domain.entity.comment.CommentRepository;
import com.Project.BoardService.domain.dto.commentDto.CommentResponseDto;
import com.Project.BoardService.domain.entity.like.LikeRepository;
import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.post.UploadFile;
import com.Project.BoardService.domain.entity.user.User;
import com.Project.BoardService.exception.advice.postAdvice.InvalidKeywordException;
import com.Project.BoardService.exception.advice.postAdvice.NotFoundPostException;
import com.Project.BoardService.domain.entity.post.PostRepository;
import com.Project.BoardService.exception.advice.userAdvice.UnauthorizedUserException;
import com.Project.BoardService.service.file.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final FileStore fileStore;

    //게시글 작성
    @Transactional
    public PostSaveResponseDto save(PostSaveRequestDto postSaveRequestDto, User user){
        List<UploadFile> uploadImages = fileStore.uploadImages(postSaveRequestDto.getImages());
        Post savePost = postRepository.save(postSaveRequestDto.toEntity(user, uploadImages));
        return PostSaveResponseDto.of(savePost);
    }

    //게시글 전체 조회
    public Page<AllPostResponseDto> findAll(Pageable pageable){
//        List<Post> all = postRepository.findTop100AllByOrderByCreateDateDesc();
//        return all.stream().map(AllPostResponseDto::of).collect(Collectors.toList());
        return postRepository.findAllPosts(pageable).map(AllPostResponseDto::of);
    }

    //특정 게시글 조회
    public FindByIdPostResponseDto findById(Long id){
        Post findPost = postRepository.findById(id)
                .orElseThrow(NotFoundPostException::new);

        List<CommentResponseDto> comment = commentRepository.findCommentsByPost(findPost).stream().map(CommentResponseDto::of).collect(Collectors.toList());
        Integer likeCount = likeRepository.countLikeByPost(findPost).orElse(0);

        return FindByIdPostResponseDto.of(findPost, comment, likeCount);
    }

    //특정 게시글 수정
    @Transactional
    public PostUpdateResponseDto update(Long id, PostUpdateRequestDto postUpdateRequestDto, User user){
        Post findPost = postRepository.findById(id)
                .orElseThrow(NotFoundPostException::new);

        if(findPost.getUser().getId()!=user.getId()){
            throw new UnauthorizedUserException();
        }

        findPost.update(postUpdateRequestDto);
        return PostUpdateResponseDto.of(findPost);
    }

    //특정 게시글 삭제
    @Transactional
    public void delete(Long id, User user){

        Post findPost = postRepository.findById(id)
                .orElseThrow(NotFoundPostException::new);

        if(findPost.getUser().getId()!=user.getId()){
            throw new UnauthorizedUserException();
        }

        postRepository.delete(findPost);
    }

    //게시글 검색 기능
    public List<AllPostResponseDto> search(String keyword){
        if(keyword.trim().isBlank()){
            throw new InvalidKeywordException();
        }
        else {
            List<Post> searchResult = postRepository.findTop100ByTitleContainingOrderByCreateDateDesc(keyword);
            return searchResult.stream().map(AllPostResponseDto::of).collect(Collectors.toList());
        }
    }
}
