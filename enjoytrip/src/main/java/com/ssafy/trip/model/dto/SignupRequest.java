package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/** 회원가입 요청 DTO */
@Schema(name = "SignupRequest",
        description = "회원 가입 시 필요한 필드")
@Getter @Setter
public class SignupRequest {

    @Schema(description = "로그인 ID (중복 불가)", example = "testId", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String id;

    @Schema(description = "평문 비밀번호", example = "Passw0rd!", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String password;

    @Schema(description = "이메일", example = "email@ssafy.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email
    private String email;

    @Schema(description = "사용자 이름", example = "손근영", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String name;

    @Schema(description = "생년월일(YYYY-MM-DD)", example = "1995-08-17")
    private String birthdate;

    @Schema(description = "성별 (M / F / OTHER)", example = "F")
    private String gender;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phonenum;
}
