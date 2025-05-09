package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.AccessDeniedException;
import com.ssafy.trip.exception.RecordNotFoundException;
import com.ssafy.trip.model.dao.CommentDao;
import com.ssafy.trip.model.dto.CommentRequest;
import com.ssafy.trip.model.dto.CommentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentDao cDao;

	@Override
	public List<CommentResponse> getCommentsByPostNo(int postNo) {
		return cDao.getCommentsByPostNo(postNo);
	}

	@Override
	@Transactional
	public int createComment(CommentRequest request) {
		if (request.getParentNo() != null) {
		    CommentResponse parent = cDao.getCommentByCommentNo(request.getParentNo());
		    if (parent == null || parent.isDeleted()) {
		        throw new RecordNotFoundException("부모 댓글이 존재하지 않거나 삭제되었습니다.");
		    }
		}
		return cDao.insertComment(request);
	}

	@Override
	@Transactional
	public int deleteComment(int commentNo , int userNo) {
		CommentResponse existing = cDao.getCommentByCommentNo(commentNo);
	    if (existing == null || existing.isDeleted()) {
	        throw new RecordNotFoundException("존재하지 않거나 이미 삭제된 댓글입니다.");
	    }
	    if (existing.getUserNo() != userNo) {
	        throw new AccessDeniedException("본인의 댓글만 수정할 수 있습니다.");
	    }
		return cDao.deleteComment(commentNo);
	}

	@Override
	@Transactional
	public int updateComment(CommentRequest request, int userNo) {
		CommentResponse existing = cDao.getCommentByCommentNo(request.getCommentNo());
	    if (existing == null || existing.isDeleted()) {
	        throw new RecordNotFoundException("존재하지 않거나 삭제된 댓글입니다.");
	    }
	    if (existing.getUserNo() != userNo) {
	        throw new AccessDeniedException("본인의 댓글만 삭제할 수 있습니다.");
	    }
		return cDao.updateComment(request);
	}

}
