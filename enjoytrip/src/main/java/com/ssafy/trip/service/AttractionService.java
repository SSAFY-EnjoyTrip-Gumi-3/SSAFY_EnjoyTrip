package com.ssafy.trip.service;

import java.util.List;

import com.ssafy.trip.model.dto.Attraction;

public interface AttractionService {
	// 특정 지역 코드와 시군구 코드로 관광지 정보 리스트를 가져오는 메서드
	List<Attraction> getAttractionByArea(int areaCode, int siGunGuCode);
	
	// 관광지 번호로 특정 관광지 정보를 가져오는 메서드
    Attraction getAttractionByNo(int attractionNo);
	
	 // 콘텐츠 타입별로 관광지 정보 리스트를 가져오는 메서드
    List<Attraction> getAttractionByContentType(int contentNo);
    
 // 특정 지역과 콘텐츠 타입별로 관광지 정보를 가져오는 메서드
    List<Attraction> getAttractionByAreaAndContentType(int sidoCode, int gugunCode, List<Integer> contentTypes);
}
