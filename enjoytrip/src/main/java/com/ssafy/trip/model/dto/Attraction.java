package com.ssafy.trip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attraction {
	private int attractionNo;
	private int contentId;
	private int contentTypeId;
	private int areaCode;
	private int siGunGuCode;
	
	private String title;
	private String image1;
	private String image2;
	private int mapLevel;
	private double latitude;
	private double longitude;
	private String tel;
	private String addr1;
	private String addr2;
	private String homepage;
	private String overview;
}
