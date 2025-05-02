package com.ssafy.trip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.exception.AuthException;
import com.ssafy.trip.exception.DuplicateIdException;
import com.ssafy.trip.model.dto.SignupRequest;
import com.ssafy.trip.model.dto.UpdateRequest;
import com.ssafy.trip.model.dto.User;
import com.ssafy.trip.model.dto.UserResponse;
import com.ssafy.trip.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController implements RestControllerHelper{

	private final UserService uService;

	@PostMapping
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest req){
		try {
			User user = User.builder()
								.id(req.getId())
								.password(req.getPassword())
								.email(req.getEmail())
								.name(req.getName())
								.birthdate(req.getBirthdate())
								.gender(req.getGender())
								.phonenum(req.getPhonenum())
								.role("USER")
								.status("ACTIVE")
								.build();
			

			uService.registUser(user);
			
			UserResponse res = UserResponse.builder()
					.id(user.getId())
					.name(user.getName())
					.email(user.getEmail())
					.userNo(user.getUserNo())
					.build();
			
			return handleSuccess(res, HttpStatus.CREATED);
		}catch(DuplicateIdException e) {
			return handleFail(e, HttpStatus.CONFLICT);
		}catch(Exception e) {
			return handleFail(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody UpdateRequest req,
			HttpSession session){
		
		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		
		if(loginUser==null) {
			return handleFail(new AuthException("로그인이 필요합니다."), HttpStatus.UNAUTHORIZED);
		}
	    
	    int userNo = loginUser.getUserNo();

		User user = User.builder()
				.userNo(userNo)
				.password(req.getPassword())
				.email(req.getEmail())
				.name(req.getName())
				.birthdate(req.getBirthdate())
				.gender(req.getGender())
				.phonenum(req.getPhonenum())
				.build();
		
		uService.updateUser(user);
		
		User updatedUser = uService.getUser(user.getId());
		
		UserResponse res = UserResponse.builder()
				.id(updatedUser.getId())
				.name(updatedUser.getName())
				.email(updatedUser.getEmail())
				.userNo(updatedUser.getUserNo())
				.build();
		
		session.setAttribute("loginUser", res);
		
		return handleSuccess(res);
	}
	
	@GetMapping
	public ResponseEntity<?> detail(HttpSession session){

		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		
		if(loginUser==null) {
			return handleFail(new AuthException("로그인이 필요합니다."), HttpStatus.UNAUTHORIZED);
		}
		return handleSuccess(loginUser);

	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(HttpSession session) {

	    UserResponse loginUser =
	            (UserResponse) session.getAttribute("loginUser");

	    if (loginUser == null) {
	        return handleFail(
	                new AuthException("로그인이 필요합니다."),
	                HttpStatus.UNAUTHORIZED);          // 401
	    }

	    int userNo = loginUser.getUserNo();
	    uService.deleteUser(userNo);                // soft-delete (status='DELETED')

	    session.invalidate();                          // 세션 만료
	    return ResponseEntity.noContent().build();     // 204 No Content
	}
	
	
}
