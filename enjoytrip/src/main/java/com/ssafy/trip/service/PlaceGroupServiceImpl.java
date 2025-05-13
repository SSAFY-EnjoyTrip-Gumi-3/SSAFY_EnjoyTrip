package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.RecordNotFoundException;
import com.ssafy.trip.model.dao.PlaceGroupDao;
import com.ssafy.trip.model.dto.PlaceGroupDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceGroupServiceImpl implements PlaceGroupService {
	
	private final PlaceGroupDao gDao;
	
	@Override
	@Transactional
	public PlaceGroupDTO createGroup(PlaceGroupDTO req) {
		if (gDao.existsGroupName(req.getUserNo(), req.getGroupName()) > 0) {
			throw new RecordNotFoundException("이미 존재하는 그룹 이름입니다.");
		}
		int groupNo = gDao.insertPlaceGroup(req.getUserNo(), req.getGroupName());
		return PlaceGroupDTO.builder()
				.groupNo(groupNo)
				.userNo(req.getUserNo())
				.GroupName(req.getGroupName())
				.build();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PlaceGroupDTO> getGroupsByUser(int userNo) {
		return gDao.selectGroupsByUser(userNo);
	}
}
