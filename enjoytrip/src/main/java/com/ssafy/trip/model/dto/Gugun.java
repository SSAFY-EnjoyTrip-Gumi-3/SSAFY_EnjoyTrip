package com.ssafy.trip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gugun {
	private int no;
	private int sidoCode;
	private int gugunCode;
	private String gugunName;
}
