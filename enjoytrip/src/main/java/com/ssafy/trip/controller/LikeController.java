package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.PopularAttractionDTO;
import com.ssafy.trip.model.dto.LikeRequestDTO;
import com.ssafy.trip.model.dto.LikeResponseDTO;
import com.ssafy.trip.service.LikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
@Tag(name = "likes" , description = "사용자가 좋아요/ 좋아요 취소 조회 API")
public class LikeController implements RestControllerHelper{
	private final LikeService lService;
	
	@Operation(summary = "좋아요/ 좋아요 취소 결과 조회",
			 description = "좋아요/ 좋아요 취소한 관광지 정보를 반환합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 좋아요/ 좋아요 취소 결과 반환")
	@PostMapping
	public ResponseEntity<LikeResponseDTO> toggleLike(@RequestBody LikeRequestDTO req){
		LikeResponseDTO resp = lService.toggleLike(req);
		return ResponseEntity.ok(resp);
	}
	
	@Operation(summary = "현재 좋아요 개수",
			 description = "관광지의 현재 좋아요 개수를 반환합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 좋아요 개수 결과 반환")
	@GetMapping("/{attractionNo}")
	public ResponseEntity<Long> getLikeCount(@PathVariable int attractionNo){
		long count = lService.getLikeCount(attractionNo);
		return ResponseEntity.ok(count);
	}
	
	@Operation(summary = "좋아요 많은 순으로 정렬된 관광지 정보",
			description = "정렬된 관광지 정보를 반환합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 정렬된 관광지 정보 반환")
	@GetMapping("/popular")
	public ResponseEntity<List<PopularAttractionDTO>> getPopularAttractions(){
		List<PopularAttractionDTO> list = lService.getPopularAttractions();
		return ResponseEntity.ok(list);
	}
}
