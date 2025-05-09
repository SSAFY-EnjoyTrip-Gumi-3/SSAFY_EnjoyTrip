package com.ssafy.trip.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.model.dao.LikeDao;
import com.ssafy.trip.model.dto.LikeRequestDTO;
import com.ssafy.trip.model.dto.LikeResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
	private final LikeDao lDao;
	
	@Override
		public long getLikeCount(int attractionNo) {
			return lDao.countLikesByAttraction(attractionNo);
		}
	
	@Override
	@Transactional
	public LikeResponseDTO toggleLike(LikeRequestDTO req) {
		boolean curLike = lDao.existsLike(req.getUserNo(), req.getAttractionNo()) > 0;
		if (req.isLike() && !curLike) {
			lDao.insertLike(req.getUserNo(), req.getAttractionNo());
		}else if (!req.isLike() && curLike) {
			lDao.deleteLike(req.getUserNo(), req.getAttractionNo());
		}
		
		long cnt = lDao.countLikesByAttraction(req.getAttractionNo());
		return new LikeResponseDTO(req.getAttractionNo(), cnt, req.isLike());
	}
}
