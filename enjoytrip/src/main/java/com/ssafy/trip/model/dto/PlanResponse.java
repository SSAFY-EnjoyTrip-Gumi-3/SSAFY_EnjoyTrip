package com.ssafy.trip.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "여행 계획 응답 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {

    @Schema(description = "계획 번호", example = "1")
    private int planNo;
    
    @Schema(description = "작성자 유저 번호", example = "1")
    private int userNo;

    @Schema(description = "계획 제목", example = "제주도 3박 4일 여행")
    private String title;

    @Schema(description = "계획 생성 시간", example = "2025-05-09T11:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "여행 계획에 포함된 관광지들")
    private List<PlannedAttraction> attractions;
}
