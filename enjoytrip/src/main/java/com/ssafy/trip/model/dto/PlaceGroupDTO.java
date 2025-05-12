package com.ssafy.trip.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceGroupDTO {
	
	private int groupNo;
	
	private int userNo;
	
	private String GroupName;
	
	private LocalDateTime createdAt;
}
