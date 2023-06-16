package com.Project.BoardService.controller;

import com.Project.BoardService.domain.Post;
import com.Project.BoardService.domain.dto.PostResponseDto;
import com.Project.BoardService.domain.dto.PostSaveRequestDto;
import com.Project.BoardService.domain.dto.PostUpdateRequestDto;
import com.Project.BoardService.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    //게시글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto save(@RequestBody PostSaveRequestDto postSaveRequestDto){
        return postService.save(postSaveRequestDto);
    }

    //게시글 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> findAll(){
        return postService.findAll();
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto findById(@PathVariable("id") Long id){
        return postService.findById(id);
    }

    //특정 게시글 수정
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto update(@PathVariable("id") Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto){
        return postService.update(id, postUpdateRequestDto);
    }

    //특정 게시글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") Long id){
        postService.delete(id);
        return "ok";
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> search(@RequestParam("keyword") String keyword){
        return postService.search(keyword);
    }

}
