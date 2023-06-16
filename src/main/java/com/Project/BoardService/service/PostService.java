package com.Project.BoardService.service;

import com.Project.BoardService.domain.Post;
import com.Project.BoardService.domain.dto.PostResponseDto;
import com.Project.BoardService.domain.dto.PostSaveRequestDto;
import com.Project.BoardService.domain.dto.PostUpdateRequestDto;
import com.Project.BoardService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시글 작성
    @Transactional
    public PostResponseDto save(PostSaveRequestDto postSaveRequestDto){
        Post savePost = postRepository.save(postSaveRequestDto.toEntity());
        return PostResponseDto.of(savePost);
    }

    //게시글 전체 조회
    public List<PostResponseDto> findAll(){
        List<Post> all = postRepository.findTop100AllByOrderByCreateDateDesc();
        return all.stream().map(PostResponseDto::of).collect(Collectors.toList());
    }

    //특정 게시글 조회
    public PostResponseDto findById(Long id){
        Post findPost = postRepository.findById(id).get();
        return PostResponseDto.of(findPost);
    }

    //특정 게시글 수정
    @Transactional
    public PostResponseDto update(Long id, PostUpdateRequestDto postUpdateRequestDto){
        Post findPost = postRepository.findById(id).get();

        findPost.update(postUpdateRequestDto);
        return PostResponseDto.of(findPost);
    }

    //특정 게시글 삭제
    @Transactional
    public void delete(Long id){
        postRepository.delete(postRepository.findById(id).get());
    }

    //게시글 검색 기능
    public List<PostResponseDto> search(String keyword){
        List<Post> searchResult = postRepository.findTop100ByTitleContainingOrderByCreateDateDesc(keyword);
        return searchResult.stream().map(PostResponseDto::of).collect(Collectors.toList());
    }
}
