package com.ssafy.trip.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.LikeRequestDTO;
import com.ssafy.trip.model.dto.LikeResponseDTO;
import com.ssafy.trip.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController implements RestControllerHelper{
	private final LikeService lService;
	
	@PostMapping
	public ResponseEntity<LikeResponseDTO> toggleLike(@RequestBody LikeRequestDTO req){
		LikeResponseDTO resp = lService.toggleLike(req);
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/{attractionNo}")
	public ResponseEntity<Map<String, Long>> getLikeCount(@PathVariable int attractionNo){
		long count = lService.getLikeCount(attractionNo);
		return ResponseEntity.ok(Collections.singletonMap("count", count));
	}
}
