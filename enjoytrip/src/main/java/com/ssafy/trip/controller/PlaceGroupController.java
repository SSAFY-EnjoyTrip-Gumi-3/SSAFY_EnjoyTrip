package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.PlaceGroupDTO;
import com.ssafy.trip.service.PlaceGroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class PlaceGroupController implements RestControllerHelper{
	private final PlaceGroupService gService;
	
	@PostMapping
	 public ResponseEntity<PlaceGroupDTO> createGroup(
	            @RequestBody PlaceGroupDTO req) {
	        return ResponseEntity.ok(gService.createGroup(req));
	    }
	
	@GetMapping
	public ResponseEntity<List<PlaceGroupDTO>> getMyGroups(
	            @RequestParam int userNo) {
	        return ResponseEntity.ok(gService.getGroupsByUser(userNo));
	    }                      
}
