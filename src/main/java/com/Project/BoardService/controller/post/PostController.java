package com.Project.BoardService.controller.post;

import com.Project.BoardService.domain.dto.postDto.PostResponseDto;
import com.Project.BoardService.domain.dto.postDto.PostSaveRequestDto;
import com.Project.BoardService.domain.dto.postDto.PostSaveResponseDto;
import com.Project.BoardService.domain.dto.postDto.PostUpdateRequestDto;
import com.Project.BoardService.domain.entity.user.User;
import com.Project.BoardService.exception.ErrorResult;
import com.Project.BoardService.jwt.LogIn;
import com.Project.BoardService.service.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글 REST API", description = "게시물에 대한 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 저장", description = "게시물 저장 API")
    @ApiResponse(responseCode = "201", description = "저장 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostSaveResponseDto save(@Validated @RequestBody PostSaveRequestDto postSaveRequestDto, @LogIn User user){
        return postService.save(postSaveRequestDto, user);
    }

    @Operation(summary = "게시물 전체 조회", description = "게시물 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "전체 조회 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> findAll(){
        return postService.findAll();
    }

    @Operation(summary = "특정 게시물 조회", description = "특정 게시물 조회 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 게시물 조회 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물", content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto findById(@Parameter(name = "id", description = "게시글 ID 값", in = ParameterIn.PATH) @PathVariable("id") Long id){
        return postService.findById(id);
    }

    @Operation(summary = "특정 게시물 수정", description = "특정 게시물 수정 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 게시물 수정 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물", content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto update(@Parameter(name = "id", description = "게시글 ID 값", in = ParameterIn.PATH) @PathVariable("id") Long id,
                                  @Validated @RequestBody PostUpdateRequestDto postUpdateRequestDto, @LogIn User user){
        return postService.update(id, postUpdateRequestDto, user);
    }

    @Operation(summary = "특정 게시물 삭제", description = "특정 게시물 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 게시물 삭제 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시물", content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@Parameter(name = "id", description = "게시글 ID 값", in = ParameterIn.PATH) @PathVariable("id") Long id, @LogIn User user){
        postService.delete(id, user);
        return "ok";
    }

    @Operation(summary = "제목 검색", description = "제목으로 게시물 검색 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검색 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "키워드 조건 미충족", content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> search(@Parameter(name="keyword", description = "검색 키워드 문자", in=ParameterIn.QUERY) @RequestParam("keyword") String keyword){
        return postService.search(keyword);
    }

}
