package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Tour API 콘텐츠 타입 DTO */
@Schema(name = "ContentType",
        description = "Tour API에서 내려오는 콘텐츠 타입 코드·이름")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentType {

    @Schema(description = "콘텐츠 타입 ID (TourAPI code)", example = "12")
    private int contentTypeNo;          // 12=관광지 · 14=문화시설 · 15=축제/공연/행사 …

    @Schema(description = "콘텐츠 타입 이름", example = "관광지")
    private String contentTypeName;
}
