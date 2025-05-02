package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.AttractionDao;
import com.ssafy.trip.model.dto.Attraction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
	private final AttractionDao aDao;
	
	@Override
	public List<Attraction> getAttractionByArea(int areaCode, int siGunGuCode) {
		return aDao.getAttractionByArea(areaCode, siGunGuCode);
	}
	
	@Override
	public Attraction getAttractionByNo(int attractionNo) {
		return aDao.getAttractionByNo(attractionNo);
	}
	
	@Override
	public List<Attraction> getAttractionByAreaAndContentType(int sidoCode, int gugunCode, int contentType) {
		return aDao.getAttractionByAreaAndContentType(sidoCode, gugunCode, contentType);
	}
	
	@Override
	public List<Attraction> getAttractionByContentType(int contentNo) {
		return aDao.getAttractionByContentType(contentNo);
	}
}
