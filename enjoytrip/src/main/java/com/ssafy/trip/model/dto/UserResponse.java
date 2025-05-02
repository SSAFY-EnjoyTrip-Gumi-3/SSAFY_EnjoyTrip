/* ───────────── UserResponse (API 응답 전용) ───────────── */
package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/** API 응답에 노출되는 안전 필드만 담은 DTO */
@Schema(name = "UserResponse",
        description = "API 응답에 사용되는 사용자 요약 DTO (민감 정보 제외)")
@Data
@Builder
public class UserResponse {

    @Schema(description = "사용자 PK", example = "7")
    private int userNo;

    @Schema(description = "로그인 ID", example = "testId")
    private String id;

    @Schema(description = "이메일", example = "email@ssafy.com")
    private String email;

    @Schema(description = "이름", example = "손근영")
    private String name;
}
