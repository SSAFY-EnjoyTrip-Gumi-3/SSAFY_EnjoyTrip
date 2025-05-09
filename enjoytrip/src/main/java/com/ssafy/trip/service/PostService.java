package com.ssafy.trip.service;

import java.util.List;

import com.ssafy.trip.model.dto.Post;
import com.ssafy.trip.model.dto.PostList;

/**
 * 게시글 관련 비즈니스 로직을 담당하는 서비스 인터페이스입니다.
 * - 게시글 작성, 조회, 수정, 삭제 기능 제공
 * - 삭제는 논리 삭제 방식(is_deleted = 1)으로 처리
 */
public interface PostService {

    /**
     * 삭제되지 않은 모든 게시글 목록을 조회합니다.
     * @return 게시글 리스트 (is_deleted = false 인 게시글만)
     */
    List<PostList> getAllPosts();

    /**
     * 특정 게시글을 조회하고, 해당 게시글에 달린 댓글 목록도 함께 반환합니다.
     * @param postId 조회할 게시글의 ID
     * @return 게시글 정보 + 댓글 리스트 포함된 Post 객체
     */
    Post getPostWithComments(int postNo);

    /**
     * 새로운 게시글을 등록합니다.
     * @param post 저장할 게시글 정보 (userId, title, content 포함)
     */
    int createPost(Post post);

    /**
     * 게시글 내용을 수정합니다.
     * @param post 수정할 게시글 정보 (postId, title, content 포함)
     * @param userNo 해당 게시글의 유저 정보 (PK)
     */
    int updatePost(Post post, int userNo);

    /**
     * 게시글을 논리적으로 삭제합니다.
     * 실제 삭제하지 않고 is_deleted 플래그만 1로 변경합니다.
     * @param postId 삭제할 게시글의 ID
     * @param userNo 해당 게시글의 유저 정보 (PK)
     */
    int deletePost(int postNo, int userNo);
}
