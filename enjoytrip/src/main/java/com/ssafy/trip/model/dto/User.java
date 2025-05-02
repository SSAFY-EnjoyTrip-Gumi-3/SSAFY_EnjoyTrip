package com.ssafy.trip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
    private int userNo;                 // 기본키
    private String id;             // 로그인 ID
    private String password;       // 비밀번호
    private String email;          // 이메일
    private String name;           // 이름
    private String birthdate;   // 생년월일
    private String gender;         // 성별 (M / F / OTHER)
    private String phonenum;       // 전화번호
    private String role;           // USER / ADMIN
    private String regdate; // 가입일시
    private String status;       // 'ACTIVE', 'INACTIVE', 'DELETED'
    
}
