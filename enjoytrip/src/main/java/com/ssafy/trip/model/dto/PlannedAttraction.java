package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "계획된 관광지 응답 DTO (관광지 + 일정 정보)")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlannedAttraction {

    @Schema(description = "관광지 정보")
    private Attraction attraction;

    @Schema(description = "해당 관광지를 방문하는 날", example = "2")
    private int day;

    @Schema(description = "하루 중 몇 번째 방문지인지", example = "1")
    private int sequence;
}
