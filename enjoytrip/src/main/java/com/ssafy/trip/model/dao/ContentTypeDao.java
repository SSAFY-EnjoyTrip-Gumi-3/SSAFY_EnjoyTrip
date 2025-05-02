package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.ContentType;

@Mapper
public interface ContentTypeDao {
	public List<ContentType> getAll();
}
