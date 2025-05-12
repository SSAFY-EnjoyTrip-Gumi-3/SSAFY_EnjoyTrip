package com.ssafy.trip.model.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "여행 계획 생성 요청 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {
    @Schema(description = "계획 제목", example = "제주도 3박 4일 여행")
    private String title;

    @Schema(description = "여행에 포함된 관광지 목록")
    private List<PlanAttractionRequest> attractions;
}
