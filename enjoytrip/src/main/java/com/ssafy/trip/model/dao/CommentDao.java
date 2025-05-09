package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.CommentRequest;
import com.ssafy.trip.model.dto.CommentResponse;

/**
 * 댓글(Comment) 관련 DB 작업을 처리하는 DAO 인터페이스입니다.
 */
@Mapper
public interface CommentDao {

    /**
     * 특정 게시글에 대한 댓글 목록을 조회합니다.
     *
     * @param postNo 조회할 게시글 번호
     * @return 해당 게시글에 달린 댓글 리스트
     */
    List<CommentResponse> getCommentsByPostNo(int postNo);

    /**
     * 댓글을 데이터베이스에 삽입합니다.
     *
     * @param commentRequest 삽입할 댓글 정보
     * @return 삽입된 행 수 (성공 시 1)
     */
    int insertComment(CommentRequest commentRequest);

    /**
     * 기존 댓글을 수정합니다.
     *
     * @param commentRequest 수정할 댓글 정보
     * @return 수정된 행 수 (성공 시 1)
     */
    int updateComment(CommentRequest commentRequest);

    /**
     * 댓글을 논리 삭제 또는 실제 삭제합니다.
     *
     * @param commentRequest 삭제할 댓글 정보
     * @return 삭제된 행 수 (성공 시 1)
     */
    int deleteComment(int commentNo);
    
    /**
     * 
     * @param commentNo 댓글 번호 (PK)
     * @return 해당 댓글
     */
    CommentResponse getCommentByCommentNo(int commentNo);
}
