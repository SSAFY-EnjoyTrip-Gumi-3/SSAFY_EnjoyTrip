package com.ssafy.trip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "회원 가입·조회·수정·탈퇴 API")
@Slf4j
public class UserController implements RestControllerHelper{

	private final UserService uService;

	@Operation(summary = "회원 가입", description = "ID/비밀번호‧이메일 등을 받아 신규 회원을 등록합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "가입 성공"),
        @ApiResponse(responseCode = "409", description = "ID 중복")
    })
	@PostMapping("/me")
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
	
	@Operation(summary = "내 정보 수정", description = "세션 로그인 사용자 기준으로 일부 정보(PATCH) 수정")
    @ApiResponse(responseCode = "200", description = "수정 완료")
	@PutMapping("/me")
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
	
	@Operation(summary = "내 정보 조회")
    @ApiResponse(responseCode = "200", description = "로그인 사용자의 프로필 반환")
	@GetMapping("/me")
	public ResponseEntity<?> detail(HttpSession session){

		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		
		if(loginUser==null) {
			return handleFail(new AuthException("로그인이 필요합니다."), HttpStatus.UNAUTHORIZED);
		}
		return handleSuccess(loginUser);

	}
	
	@Operation(summary = "회원 탈퇴", description = "status='DELETED' 로 소프트 삭제 후 세션 만료")
    @ApiResponse(responseCode = "204", description = "탈퇴 완료")
	@DeleteMapping("/me")
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
