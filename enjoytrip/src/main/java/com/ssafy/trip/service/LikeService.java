package com.ssafy.trip.service;

import com.ssafy.trip.model.dto.LikeRequestDTO;
import com.ssafy.trip.model.dto.LikeResponseDTO;

public interface LikeService {

	LikeResponseDTO toggleLike(LikeRequestDTO req);
	
	long getLikeCount(int attractionNo);
}
