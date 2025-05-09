package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.Post;
import com.ssafy.trip.model.dto.PostList;

/**
 * 게시글 관련 DB 접근을 위한 MyBatis DAO 인터페이스입니다.
 */
@Mapper
public interface PostDao {

    /**
     * 삭제되지 않은 게시글 목록을 조회합니다.
     * @return 게시글 요약 정보 리스트 (PostList DTO)
     */
    List<PostList> getAllPosts();

    /**
     * 특정 게시글 상세 정보를 조회합니다.
     * @param postNo 게시글 no
     * @return 게시글 상세 정보 (Post DTO)
     */
    Post getPostByPostNo(int postNo);

    /**
     * 게시글을 DB에 삽입합니다.
     * @param post 저장할 게시글 객체
     * @return     INSERT된 행 수(성공=1)  
     */
    int insertPost(Post post);

    /**
     * 게시글을 수정합니다.
     * @param post 수정할 게시글 객체
     * @return      UPDATE된 행 수(성공=1)  
     */
    int updatePost(Post post);

    /**
     * 게시글을 논리 삭제 처리합니다. (is_deleted = 1)
     * @param postId 삭제할 게시글 ID
     * @return      DELETE된 행 수(성공=1)  
     */
    int logicalDeletePost(int postNo);
}  
