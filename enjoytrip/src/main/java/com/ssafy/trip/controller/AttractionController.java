package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.Attraction;
import com.ssafy.trip.model.dto.ContentType;
import com.ssafy.trip.model.dto.Gugun;
import com.ssafy.trip.model.dto.Sido;
import com.ssafy.trip.service.AreaService;
import com.ssafy.trip.service.AttractionService;
import com.ssafy.trip.service.ContentTypeService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attraction")
@Getter @Setter
@Slf4j
public class AttractionController implements RestControllerHelper {
	private final AttractionService attractionService;
	private final ContentTypeService contentService;
	private final AreaService areaService;
	private final String keyVworld = "7E7EFCBE-1F93-343B-8A9A-8868B3362160";
	private final String keySgisServiceId = "fd992959b88b4572ad31";
	private final String keySgisSecurity = "4b8165f5b0704ff88a32";
	private final String keyData = "zUUH8MYcYzHqcPqEKVbqYbvc5yOe5J60W%2F3qpVd6Fy6lUyARl9gk%2F2JHqdeGDq2Dgocy1fkjwiaI3Rq0qGYC1A%3D%3D";
	
	/** 초기 데이터 조회: 시도 목록, 콘텐츠 타입, API 키 */
    @GetMapping("/init")
    public ResponseEntity<InitResponse> init() {
        List<Sido> sidos = areaService.getSido();
        List<ContentType> types = contentService.getAll();
        InitResponse resp = new InitResponse(
            sidos, types,
            keyVworld, keySgisServiceId, keySgisSecurity, keyData
        );
        return ResponseEntity.ok(resp);
    }

    /** 특정 시도(sido)의 구군(gugun) 목록 조회 */
    @GetMapping("/gugun/{sidoCode}")
    public ResponseEntity<List<Gugun>> getGugun(@PathVariable int sidoCode) {
        List<Gugun> guguns = areaService.getGugun(sidoCode);
        return ResponseEntity.ok(guguns);
    }

    /** 시도, 구군, 콘텐츠 타입에 따른 관광지 검색 */
    @GetMapping("/search")
    public ResponseEntity<List<Attraction>> search(
            @RequestParam int sidoCode,
            @RequestParam int gugunCode,
            @RequestParam int contentType) {
        List<Attraction> attractions =
            attractionService.getAttractionByAreaAndContentType(sidoCode, gugunCode, contentType);
        return ResponseEntity.ok(attractions);
    }

    /** InitResponse: init API 호출 시 반환할 DTO */
    public static class InitResponse {
        private List<Sido> sidos;
        private List<ContentType> contentTypes;
        private String keyVworld;
        private String keySgisServiceId;
        private String keySgisSecurity;
        private String keyData;

        public InitResponse(List<Sido> sidos, List<ContentType> contentTypes,
                            String keyVworld, String keySgisServiceId,
                            String keySgisSecurity, String keyData) {
            this.sidos = sidos;
            this.contentTypes = contentTypes;
            this.keyVworld = keyVworld;
            this.keySgisServiceId = keySgisServiceId;
            this.keySgisSecurity = keySgisSecurity;
            this.keyData = keyData;
        }
    }
}
