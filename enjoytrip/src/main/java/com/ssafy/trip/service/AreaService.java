package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.GugunDao;
import com.ssafy.trip.model.dao.SidoDao;
import com.ssafy.trip.model.dto.Gugun;
import com.ssafy.trip.model.dto.Sido;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaService {
	private final SidoDao sDao;
	private final GugunDao gDao;
	
	public List<Sido> getSido(){
		return sDao.getAll();
	}
	
	public List<Gugun> getGugun(int sidoCode){
		return gDao.getBySido(sidoCode);
	}
}
