package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "댓글 응답 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    @Schema(description = "댓글 고유 번호", example = "1001")
    private int commentNo;

    @Schema(description = "게시글 번호", example = "42")
    private int postNo;

    @Schema(description = "작성자 ID", example = "ssafyUser")
    private String writer;

    @Schema(description = "댓글 내용", example = "정말 유익한 글입니다!")
    private String content;

    @Schema(description = "부모 댓글 번호 (없으면 null)", example = "1")
    private Integer parentNo;

    @Schema(description = "댓글 작성 시간", example = "2024-05-07 10:32")
    private String createdAt;

    @Schema(description = "댓글 수정 시간", example = "2024-05-07 10:40")
    private String updatedAt;
}
