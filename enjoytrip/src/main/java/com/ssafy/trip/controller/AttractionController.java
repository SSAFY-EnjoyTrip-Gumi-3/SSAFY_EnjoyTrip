package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.config.ApiKeyPropertiesConfig;
import com.ssafy.trip.model.dto.Attraction;
import com.ssafy.trip.model.dto.ContentType;
import com.ssafy.trip.model.dto.Gugun;
import com.ssafy.trip.model.dto.InitResponse;
import com.ssafy.trip.model.dto.Sido;
import com.ssafy.trip.service.AreaService;
import com.ssafy.trip.service.AttractionService;
import com.ssafy.trip.service.ContentTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attractions")
@Getter @Setter
@Tag(name = "Attraction", description = "관광지·지역·콘텐츠 조회 API")
@Slf4j
public class AttractionController implements RestControllerHelper {
	private final AttractionService attractionService;
	private final ContentTypeService contentService;
	private final AreaService areaService;
	private final ApiKeyPropertiesConfig apiKeys;
	
	/** 초기 데이터 조회: 시도 목록, 콘텐츠 타입, API 키 */
	 @Operation(summary = "초기 데이터 조회",
             description = "시도 목록 · 콘텐츠 타입 목록 · 공공 API 키를 한 번에 가져옵니다.")
  @ApiResponse(responseCode = "200", description = "성공적으로 초기 데이터를 반환")
  @GetMapping("/init")
  public ResponseEntity<InitResponse> init() {
      List<Sido> sidos = areaService.getSido();
      List<ContentType> types = contentService.getAll();
      InitResponse resp = new InitResponse(
          sidos, types,
          apiKeys.getVworldKey(),
          apiKeys.getServiceId(),
          apiKeys.getSecurity(),
          apiKeys.getDataKey()
      );
      return ResponseEntity.ok(resp);
  }
	
	/** 특정 시도와 구군에 따른 관광지 목록 조회 */
	@Operation(
				summary = "시도, 구군에 따른 관광지 검색",
				description = "시도, 구군 조건으로 등록되어 있는 관광지 목록을 조회합니다."
			)
			@ApiResponses({
				@ApiResponse(responseCode = "200", description = "시도, 구군에 따른 관광지 목록 반환"),
				@ApiResponse(responseCode = "404", description = "시도 코드 또는 구군 코드가 잘못된 경우")
			})
	@GetMapping("/sido/{sidoCode}/gugun/{gugunCode}")
	public ResponseEntity<List<Attraction>> searchBySidoAndGugun(
			@Parameter(name="sidoCode", in=ParameterIn.PATH, required=true,
            description="시도 코드", example="1")
			@PathVariable int sidoCode,

			@Parameter(name="gugunCode", in=ParameterIn.PATH, required=true,
            description="구군 코드", example="1")
			@PathVariable int gugunCode){
		List<Attraction> attractions = attractionService.getAttractionByArea(sidoCode, gugunCode);
		return ResponseEntity.ok(attractions);
	}
	
    /** 특정 시도(sido)의 구군(gugun) 목록 조회 */
	@Operation(
		        summary = "구군 목록 조회",
		        description = "시도 코드를 Path Variable로 받아 해당 시도의 구군 목록을 반환합니다."
		    )
		    @ApiResponses({
		        @ApiResponse(responseCode = "200", description = "구군 목록 반환"),
		        @ApiResponse(responseCode = "404", description = "시도 코드가 잘못된 경우")
		    })
		    @Parameter(name = "sidoCode", description = "시도 코드", example = "1")
    @GetMapping("/sido/{sidoCode}")
    public ResponseEntity<List<Gugun>> getGugun(@PathVariable int sidoCode) {
        List<Gugun> guguns = areaService.getGugun(sidoCode);
        return ResponseEntity.ok(guguns);
    }

    /** 시도, 구군, 콘텐츠 타입에 따른 관광지 검색 */
	@Operation(
	        summary = "시도, 구군, 콘텐츠 타입에 따른 관광지 검색",
	        description = "시도·구군·콘텐츠 타입 조건으로 관광지 목록을 조회합니다."
	    )
	    @Parameters({
	        @Parameter(name = "sidoCode",   description = "시도 코드",   example = "1"),
	        @Parameter(name = "gugunCode",  description = "구군 코드",  example = "1"),
	        @Parameter(name = "contentTypes",description = "콘텐츠 타입(다중)", example = "[12, 14]")
	    })
    @GetMapping("/search")
    public ResponseEntity<List<Attraction>> search(
            @RequestParam int sidoCode,
            @RequestParam int gugunCode,
            @RequestParam("contentTypes") List<Integer> contentTypes) {
        List<Attraction> attractions =
            attractionService.getAttractionByAreaAndContentType(sidoCode, gugunCode, contentTypes);
        return ResponseEntity.ok(attractions);
    }
}
