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

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements RestControllerHelper{
	
	private final UserService uService;
	
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
	
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return handleSuccess("LOGOUT");
    }

}
