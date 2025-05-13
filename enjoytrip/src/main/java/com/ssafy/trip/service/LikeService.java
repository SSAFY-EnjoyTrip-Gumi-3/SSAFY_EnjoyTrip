package com.ssafy.trip.service;

import java.util.List;

import com.ssafy.trip.model.dto.PopularAttractionDTO;
import com.ssafy.trip.model.dto.LikeRequestDTO;
import com.ssafy.trip.model.dto.LikeResponseDTO;

public interface LikeService {

	/**
	 * 사용자로 부터 관광지의 좋아요/ 좋아요 취소 상태를 변경합니다.
	 * @return 좋아요 누른 관광지 번호, 개수, 상태를 포함한 LikeResponseDTO 객체 반환
	 * */
	LikeResponseDTO toggleLike(LikeRequestDTO req);

	/**
	 * 현재 좋아요 개수를 반환합니다
	 * @return 관광지의 좋아야 개수 반환
	 * */
	long getLikeCount(int attractionNo);
	
	/**
	 * 좋아요가 많은순으로 정렬할 관광지 정보를 반환합니다.
	 * @return 정렬된 관광지 list 반환
	 * */
	List<PopularAttractionDTO> getPopularAttractions();
}
