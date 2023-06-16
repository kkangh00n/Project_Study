package com.Project.BoardService.controller;

import com.Project.BoardService.domain.Post;
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
    public Post save(@RequestBody Post post){
        return postService.save(post);
    }

    //게시글 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findAll(){
        return postService.findAll();
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post findById(@PathVariable("id") Long id){
        return postService.findById(id);
    }

    //특정 게시글 수정
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable("id") Long id, @RequestBody Post post){
        return postService.update(id, post);
    }

    //특정 게시글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") Long id){
        postService.delete(id);
        return "ok";
    }

}
