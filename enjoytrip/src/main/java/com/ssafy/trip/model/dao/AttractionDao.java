package com.ssafy.trip.model.dao;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trip.model.dto.Attraction;

@Mapper
public interface AttractionDao {
	
	// 관광지 정보를 삽입하는 메서드
	void insertAttraction(Attraction attraction);
	
	// 특정 지역 코드와 시군구 코드로 관광지 정보를 조회하는 메서드
	ArrayList<Attraction> getAttractionByArea(int areaCode, int siGunGuCode);

	// 특정 지역 코드(시도 코드)로 관광지 정보를 조회하는 메서드
	ArrayList<Attraction> getAttractionBySidoCode(int areaCode);
	
	// 특정 지역 코드(시도코드), 시군구 코드, 콘텐츠 타입별로 관광지 정보를 조회하는 메서드
	
	 // 콘텐츠 타입별로 관광지 정보를 조회하는 메서드
    ArrayList<Attraction> getAttractionByContentType(int contentNo);
    
    // 관광지 번호로 특정 관광지 정보를 조회하는 메서드
    Attraction getAttractionByNo(int attractionNo);
    
    // 특정 지역 코드, 시군구 코드의 콘텐츠 타입별로 관광지를 조회하는 메서드
    List<Attraction> getAttractionByAreaAndContentType( 
    		@Param("areaCode")      int sidoCode,
    	    @Param("siGunGuCode")   int gugunCode,
    	    @Param("contentTypeIds") List<Integer> contentTypeIds);
}
