package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 좋아요 / 좋아요 취소(LikeRequestDTO) 요청시 전송할 DTO*/
@Schema(name = "LikeRequestDTO", description = "클라이언트가 좋아요/ 좋아요 취소 요청 시 전송할 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequestDTO {
	
	@Schema(description = "user 식별 번호", example = "1")
	private int userNo;
	
	@Schema(description = "관광지 식별 번호", example = "56644")
	private int attractionNo;
	
	@Schema(description = "좋아요 상태", example = "true")
	private boolean like;
	
	@Schema(description = "사용자가 지정한 좋아요 그룹이름", example = "hotplace")
	private String groupName;
}
