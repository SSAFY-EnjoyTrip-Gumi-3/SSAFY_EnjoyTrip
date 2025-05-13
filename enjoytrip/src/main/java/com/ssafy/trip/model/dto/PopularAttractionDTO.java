package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="PopularAttractionDTO", description="인기 관광지 상세 및 좋아요 정보")
public class PopularAttractionDTO {
    
    @Schema(description="관광지 식별 번호", example="1024")
    private int attractionNo;
    
    @Schema(description="해당 관광지의 좋아요 수", example="250")
    private long likeCount;
    
    @Schema(description="관광지 제목", example="가회동성당")
    private String title;

    @Schema(description="첫 번째 이미지 URL", example="http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg")
    private String firstImage1;

    @Schema(description="위도", example="37.58208588280000000")
    private double latitude;

    @Schema(description="경도", example="126.98466168560000000")
    private double longitude;
    
    @Schema(description = "설명", 
    		example = "가회동성당이 위치한 북촌 일대는 최초의 선교사 주문모(周文謨, 야고보) 신부가 조선에 밀입국하여 "
    				+ "1795년 4월 5일 부활 대축일에 최인길(崔仁吉, 마티아)의 집에서 조선 땅에서의 ‘첫 미사’를 집전한 지역이다. "
    				+ "본당 관할구역은 주문모 신부가 강완숙(姜完淑, 골롬바)의 집에 숨어 지내면서 사목활동을 "
    				+ "펼쳤던 지역으로서 한국 교회사에서 매우 중요한 의미가 있다.정식으로 본당이 된 것은 1949년이고, "
    				+ "이후 1954년에 성전이 완공되었다. "
    				+ "하지만 성전이 낡아 2011년부터 옛 성전을 허물고 현재의 새 성전을 짓게 되었다. 2013년 11월 21일 준공되었고, "
    				+ "준공 3일 후인 24일(그리스도 왕 대축일)에 입주하여 입주 미사를 봉헌하였다. "
    				+ "현재의 동서양 건축양식이 어우러진 새 성전은 과거의 역사를 되살리고자 "
    				+ "2014년 4월 20일 부활 대축일에 서울교구장 염수정 추기경님에 의하여 축성되었다.(출처 : 가회동성당 홈페이지)")
    private String overview;
    
    @Schema(description = "주소", example = "서울특별시 종로구 북촌로 57 (가회동)")
    private String addr1;
}
