package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.RecordNotFoundException;
import com.ssafy.trip.model.dao.CommentDao;
import com.ssafy.trip.model.dao.PostDao;
import com.ssafy.trip.model.dto.CommentResponse;
import com.ssafy.trip.model.dto.Post;
import com.ssafy.trip.model.dto.PostList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	
	private final PostDao pDao;
	private final CommentDao cDao;

	@Override
	public List<PostList> getAllPosts() { 
		
		return pDao.getAllPosts();	
	}

	@Override
	public Post getPostWithComments(int postId) {
		Post post = pDao.getPostById(postId);
		
		if(post==null || post.isDeleted()) {
			throw new RecordNotFoundException("존재하지 않거나 삭제된 게시글입니다.");
		}
		List<CommentResponse> comments = cDao.getCommentsByPostId(postId);
		post.setComments(comments);
		
		return post;
	}

	@Override
	@Transactional
	public int createPost(Post post) {
		return pDao.insertPost(post);
	}

	@Override
	@Transactional
	public int updatePost(Post post) {
	    Post existing = pDao.getPostById(post.getPostNo());
	    if (existing == null || existing.isDeleted()) {
	        throw new RecordNotFoundException("존재하지 않거나 삭제된 게시글입니다.");
	    }
	    return pDao.updatePost(post);
	}

	@Override
	@Transactional
	public int deletePost(int postId) {
	    Post existing = pDao.getPostById(postId);
	    if (existing == null || existing.isDeleted()) {
	        throw new RecordNotFoundException("존재하지 않거나 이미 삭제된 게시글입니다.");
	    }
	    return pDao.logicalDeletePost(postId);
	}

}
