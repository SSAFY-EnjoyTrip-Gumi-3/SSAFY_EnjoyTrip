package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.ContentTypeDao;
import com.ssafy.trip.model.dto.ContentType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentTypeService {
	private final ContentTypeDao cDao;
	
	public List<ContentType> getAll(){
		return cDao.getAll();
	}
}
