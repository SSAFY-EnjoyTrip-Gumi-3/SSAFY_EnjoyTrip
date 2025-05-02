/* ───────────── UpdateRequest ───────────── */
package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/** 내 정보 수정(PATCH) DTO */
@Schema(name = "UpdateRequest",
        description = "회원 정보 수정에 사용되는 DTO — 비어 있는 필드는 변경하지 않습니다.")
@Getter @Setter
public class UpdateRequest {

    @Schema(description = "새 비밀번호(선택)", example = "newPass")
    private String password;                       // 선택 → null 이면 그대로 유지

    @Schema(description = "이메일(선택)", example = "new@mail.com")
    @Email
    private String email;

    @Schema(description = "이름(선택)", example = "손근영")
    private String name;

    @Schema(description = "생년월일(선택, YYYY-MM-DD)", example = "1995-08-17")
    private String birthdate;

    @Schema(description = "성별(선택)", example = "F")
    private String gender;

    @Schema(description = "전화번호(선택)", example = "010-5678-4321")
    private String phonenum;
}
