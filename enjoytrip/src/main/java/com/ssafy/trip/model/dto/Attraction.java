package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 관광지(Attraction) DTO */
@Schema(name = "Attraction",
        description = "관광지 기본 정보 + 위치 · 연락처 · 이미지 · 소개")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attraction {

    /* === PK & 코드값 === */
    @Schema(description = "명소 PK(AUTO_INCREMENT)", example = "12345")
    private int attractionNo;

    @Schema(description = "TourAPI 콘텐츠 ID", example = "2754612")
    private int contentId;

    @Schema(description = "콘텐츠 타입 ID (12=관광지·14=문화시설…)", example = "12")
    private int contentTypeId;

    @Schema(description = "시·도 코드", example = "1")
    private int areaCode;

    @Schema(description = "시·군·구 코드", example = "2")
    private int siGunGuCode;

    /* === 문자열 정보 === */
    @Schema(description = "명소명", example = "경복궁")
    private String title;

    @Schema(description = "대표 이미지 URL", example = "https://…/image1.jpg")
    private String image1;

    @Schema(description = "서브 이미지 URL", example = "https://…/image2.jpg")
    private String image2;

    /* === 위치 & 지도 === */
    @Schema(description = "지도 레벨(Zoom Level)", example = "6")
    private int mapLevel;

    @Schema(description = "위도", example = "37.578841")
    private double latitude;

    @Schema(description = "경도", example = "126.977031")
    private double longitude;

    /* === 부가 정보 === */
    @Schema(description = "전화번호", example = "02-3700-3900")
    private String tel;

    @Schema(description = "주소1(도로명·지번 등)", example = "서울특별시 종로구 세종로 1-1")
    private String addr1;

    @Schema(description = "주소2(상세 주소)", example = "")
    private String addr2;

    @Schema(description = "공식 홈페이지 URL", example = "https://royalpalace.go.kr")
    private String homepage;

    @Schema(description = "관광지 소개문", example = "조선 시대의 궁궐…")
    private String overview;
}
