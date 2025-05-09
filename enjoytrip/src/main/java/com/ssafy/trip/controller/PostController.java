package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.Post;
import com.ssafy.trip.model.dto.PostList;
import com.ssafy.trip.security.AuthContext;
import com.ssafy.trip.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "게시글 작성/조회/수정/삭제 API")
public class PostController implements RestControllerHelper {

    private final PostService pService;
    private final AuthContext authContext;

    @Operation(summary = "게시글 목록 조회", description = "삭제되지 않은 게시글들을 모두 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<?> list() {
        List<PostList> posts = pService.getAllPosts();
        return handleSuccess(posts);
    }

    @Operation(summary = "게시글 상세 조회", description = "해당 ID의 게시글 상세정보 및 댓글을 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "존재하지 않거나 삭제된 게시글")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable int id) {
        Post post = pService.getPostWithComments(id);
        return handleSuccess(post);
    }

    @Operation(summary = "게시글 작성", description = "게시글을 새로 작성합니다.")
    @ApiResponse(responseCode = "201", description = "작성 완료")
    @PostMapping
    public ResponseEntity<?> write(@Valid @RequestBody Post post) {
        pService.createPost(post);
        return handleSuccess("게시글이 작성되었습니다.", HttpStatus.CREATED);
    }

    @Operation(summary = "게시글 수정", description = "기존 게시글을 수정합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "수정 완료"),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글")
    })
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Post post, HttpSession session) {
        pService.updatePost(post, authContext.getCurrentUserNo(session));
        return handleSuccess("게시글이 수정되었습니다.");
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 논리적으로 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "삭제 완료"),
        @ApiResponse(responseCode = "404", description = "이미 삭제되었거나 존재하지 않는 게시글")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, HttpSession session) {
        pService.deletePost(id, authContext.getCurrentUserNo(session));
        return handleSuccess("게시글이 삭제되었습니다.");
    }
}  
