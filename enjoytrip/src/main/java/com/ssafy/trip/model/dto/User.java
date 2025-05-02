/* ───────────── User 엔터티 DTO ───────────── */
package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/** 내부 도메인용 User 엔터티 DTO */
@Schema(name = "User",
        description = "DB users 테이블과 매핑되는 엔터티 DTO (API 응답엔 대부분 UserResponse 사용)")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Schema(description = "사용자 PK", example = "7")
    private int userNo;

    @Schema(description = "로그인 ID", example = "testId")
    private String id;

    @Schema(description = "비밀번호", example = "password")
    private String password;

    @Schema(description = "이메일", example = "email@ssafy.com")
    private String email;

    @Schema(description = "이름", example = "손근영")
    private String name;

    @Schema(description = "생년월일", example = "1995-08-17")
    private String birthdate;

    @Schema(description = "성별 (M / F / OTHER)", example = "F")
    private String gender;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phonenum;

    @Schema(description = "권한: USER / ADMIN", example = "USER")
    private String role;

    @Schema(description = "가입일시", example = "2025-05-02 14:30:11")
    private String regdate;

    @Schema(description = "계정 상태", example = "ACTIVE")
    private String status;
}
