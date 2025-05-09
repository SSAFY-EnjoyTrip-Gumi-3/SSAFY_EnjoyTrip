package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.model.dto.CommentRequest;
import com.ssafy.trip.model.dto.CommentResponse;
import com.ssafy.trip.security.AuthContext;
import com.ssafy.trip.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "댓글 작성/조회/수정/삭제 API")
public class CommentController implements RestControllerHelper {

    private final CommentService cService;
    private final AuthContext authContext;

    @Operation(summary = "특정 게시글의 댓글 목록 조회", description = "해당 게시글 번호(postNo)에 달린 모든 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{postNo}")
    public ResponseEntity<?> list(@PathVariable int postNo) {
        List<CommentResponse> comments = cService.getCommentsByPostNo(postNo);
        return handleSuccess(comments);
    }

    @Operation(summary = "댓글 작성", description = "댓글을 작성합니다. 로그인한 사용자 기준으로 등록됩니다.")
    @ApiResponse(responseCode = "201", description = "작성 완료")
    @PostMapping
    public ResponseEntity<?> write(@Valid @RequestBody CommentRequest request, HttpSession session) {
        request.setUserNo(authContext.getCurrentUserNo(session));
        cService.createComment(request);
        return handleSuccess("댓글이 작성되었습니다.", HttpStatus.CREATED);
    }

    @Operation(summary = "댓글 수정", description = "본인이 작성한 댓글만 수정할 수 있습니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "수정 완료"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지 않거나 삭제된 댓글")
    })
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CommentRequest request, HttpSession session) {
        int userNo = authContext.getCurrentUserNo(session);
        cService.updateComment(request, userNo);
        return handleSuccess("댓글이 수정되었습니다.");
    }

    @Operation(summary = "댓글 삭제", description = "본인이 작성한 댓글만 삭제할 수 있습니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "삭제 완료"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지 않거나 이미 삭제된 댓글")
    })
    @DeleteMapping("/{commentNo}")
    public ResponseEntity<?> delete(@PathVariable int commentNo, HttpSession session) {
        int userNo = authContext.getCurrentUserNo(session);
        cService.deleteComment(commentNo, userNo);
        return handleSuccess("댓글이 삭제되었습니다.");
    }
}
