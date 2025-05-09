package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.CommentResponse;

@Mapper
public interface CommentDao {
	
	List<CommentResponse> getCommentsByPostNo(int postNo);
	
}
