package com.ssafy.trip.service;

import java.util.List;

import com.ssafy.trip.model.dto.PlaceGroupDTO;

public interface PlaceGroupService {
	
	PlaceGroupDTO createGroup(PlaceGroupDTO req);
	
	List<PlaceGroupDTO> getGroupsByUser(int userNo);
}
