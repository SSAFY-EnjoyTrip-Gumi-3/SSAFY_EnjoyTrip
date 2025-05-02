package com.ssafy.trip.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.LoginDto;
import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.model.dto.UserResponse;
import com.ssafy.trip.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "로그인 · 로그아웃 API")
public class AuthController implements RestControllerHelper{
	
	private final UserService uService;
	
	@Operation(summary = "로그인", description = "ID·비밀번호로 로그인 후 세션에 사용자 정보를 저장합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "401", description = "아이디/비밀번호 불일치")
    })
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto req, HttpSession session){
		try {
			User user = uService.login(req.getId(), req.getPassword());
			UserResponse res = UserResponse.builder()
											.id(user.getId())
											.name(user.getName())
											.email(user.getEmail())
											.userNo(user.getUserNo())
											.build();
			session.setAttribute("loginUser", res);
			return handleSuccess(res);
		}catch(DataAccessException e) {
			return handleFail(e);
		}
		
	}
	
	@Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return handleSuccess("LOGOUT");
    }

}
