package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "여행 계획에 포함되는 관광지 요청 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanAttractionRequest {

    @Schema(description = "관광지 고유 번호", example = "1")
    private int attractionNo;

    @Schema(description = "여행 몇 번째 날에 해당하는지", example = "1")
    private int day;

    @Schema(description = "해당 일차 내에서의 방문 순서", example = "2")
    private int sequence;
}
