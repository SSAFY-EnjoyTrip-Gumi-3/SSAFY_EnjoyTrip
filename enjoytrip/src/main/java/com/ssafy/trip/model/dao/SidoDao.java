package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.Sido;

@Mapper
public interface SidoDao {
	public List<Sido> getAll();
}
