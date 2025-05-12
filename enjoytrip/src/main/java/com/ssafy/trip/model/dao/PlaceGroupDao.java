package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trip.model.dto.PlaceGroupDTO;

@Mapper
public interface PlaceGroupDao {
	
	int insertPlaceGroup(@Param("userNo") int userNo,
						 @Param("groupName") String groupName);
	
	List<PlaceGroupDTO> selectGroupsByUser(@Param("userNo") int userNo);
	
	int existsGroupName(@Param("userNo") int userNo, 
						@Param("groupName") String groupName);
}
