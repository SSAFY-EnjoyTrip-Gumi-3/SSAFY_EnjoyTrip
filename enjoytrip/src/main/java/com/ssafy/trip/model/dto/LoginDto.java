package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/** 로그인 요청 DTO */
@Schema(name = "LoginRequest",
        description = "로그인 시 전송되는 ID·비밀번호 DTO")
@Getter @Setter
public class LoginDto {

    @Schema(description = "로그인 ID", example = "user1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String id;

    @Schema(description = "평문 비밀번호", example = "pass1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String password;
}
