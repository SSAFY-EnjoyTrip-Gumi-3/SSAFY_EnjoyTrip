package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.AccessDeniedException;
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
	public Post getPostWithComments(int postNo) {
		Post post = pDao.getPostByPostNo(postNo);
		
		if(post==null || post.isDeleted()) {
			throw new RecordNotFoundException("존재하지 않거나 삭제된 게시글입니다.");
		}
		List<CommentResponse> comments = cDao.getCommentsByPostNo(postNo);
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
	public int updatePost(Post post, int userNo) {
	    Post existing = pDao.getPostByPostNo(post.getPostNo());
	    if (existing == null || existing.isDeleted()) {
	        throw new RecordNotFoundException("존재하지 않거나 삭제된 게시글입니다.");
	    }
	    if(existing.getUserNo() != userNo) {
	    	throw new AccessDeniedException("본인의 게시글만 수정할 수 있습니다.");
	    }
	    return pDao.updatePost(post);
	}

	@Override
	@Transactional
	public int deletePost(int postNo, int userNo) {
	    Post existing = pDao.getPostByPostNo(postNo);
	    if (existing == null || existing.isDeleted()) {
	        throw new RecordNotFoundException("존재하지 않거나 이미 삭제된 게시글입니다.");
	    }
	    if(existing.getUserNo() != userNo) {
	    	throw new AccessDeniedException("본인의 게시글만 삭제할 수 있습니다.");
	    }
	    return pDao.logicalDeletePost(postNo);
	}

}
