package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.Gugun;

@Mapper
public interface GugunDao {
	public List<Gugun> getBySido(int sidoCode);
}
