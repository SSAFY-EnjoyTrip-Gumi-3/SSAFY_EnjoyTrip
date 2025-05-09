package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 좋아요 / 좋아요 취소(LikeResponseDTO) 응답시 전송할 DTO*/
@Schema(name = "LikeResponseDTO", description = "서버에서 클라이언트로 최종 응답을 전송할 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDTO {
	
	@Schema(description = "관광지 식별 번호", example = "15")
	private int attractionNo;
	
	@Schema(description = "현재 총 좋아요 수", example = "20")
	private long likeCount;
	
	@Schema(description = "해당 사용자의 현재 좋아요 상태", example = "true")
	private boolean liked;
}
