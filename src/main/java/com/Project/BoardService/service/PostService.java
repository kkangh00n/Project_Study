package com.Project.BoardService.service;

import com.Project.BoardService.domain.Post;
import com.Project.BoardService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시글 작성
    @Transactional
    public Post save(Post post){
        return postRepository.save(post);
    }

    //게시글 전체 조회
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    //특정 게시글 조회
    public Post findById(Long id){
        return postRepository.findById(id).get();
    }

    //특정 게시글 수정
    @Transactional
    public Post update(Long id, Post post){
        Post findPost = postRepository.findById(id).get();
        findPost.update(post);
        return findPost;
    }

    //특정 게시글 삭제
    @Transactional
    public void delete(Long id){
        postRepository.delete(postRepository.findById(id).get());
    }

}
