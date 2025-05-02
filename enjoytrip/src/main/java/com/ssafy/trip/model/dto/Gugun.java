package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 구·군(시군구) 코드 DTO */
@Schema(name = "Gugun",
        description = "행정구역 ‟시·군·구” 정보를 담는 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gugun {

    @Schema(description = "구·군 PK (AUTO_INCREMENT)", example = "101")
    private int no;

    @Schema(description = "시·도 코드", example = "1")
    private int sidoCode;

    @Schema(description = "구·군 코드", example = "3")
    private int gugunCode;

    @Schema(description = "구·군 이름", example = "종로구")
    private String gugunName;
}
