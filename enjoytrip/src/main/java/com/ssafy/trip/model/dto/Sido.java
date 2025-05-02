package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 시·도 코드 DTO */
@Schema(name = "Sido", description = "행정구역 ‟시·도” 정보를 담는 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sido {

    @Schema(description = "시·도 PK (AUTO_INCREMENT)", example = "1")
    private int no;

    @Schema(description = "시·도 코드", example = "11")   // 11=서울특별시
    private int sidoCode;

    @Schema(description = "시·도 이름", example = "서울특별시")
    private String sidoName;
}
