package com.ssafy.trip.service;

import java.util.List;

import com.ssafy.trip.model.dto.CommentRequest;
import com.ssafy.trip.model.dto.CommentResponse;

public interface CommentService {

    /**
     * 특정 게시글의 모든 댓글을 조회합니다.
     * @param postId 게시글 ID
     * @return 댓글 응답 DTO 리스트
     */
    List<CommentResponse> getCommentsByPostNo(int postNo);

    /**
     * 댓글을 작성합니다.
     * @param request 댓글 작성 요청 DTO
     * @param writer 작성자 ID (세션 또는 토큰 기반)
     * @return 등록된 댓글의 고유 번호
     */
    int writeComment(CommentRequest request, String writer);

    /**
     * 댓글을 삭제합니다. (논리 삭제 또는 실제 삭제 여부는 구현에 따라 다름)
     * @param commentId 삭제할 댓글 ID
     * @param userId 요청한 사용자 ID
     * @return 삭제된 개수
     */
    int deleteComment(int commentId, String userId);
}
