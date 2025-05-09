package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "댓글 작성 요청 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
	
	@Schema(description = "댓글 고유 번호", example = "1")
    private int commentNo;

    @Schema(description = "게시글 번호", example = "1")
    private int postNo;

    @Schema(description = "댓글 내용", example = "정말 유익한 글입니다!")
    private String content;

    @Schema(description = "부모 댓글 번호 (일반 댓글일 경우 null)", example = "null")
    private Integer parentNo;
    
    @Schema(description = "작성자 no (PK)", example = "1")
    private int userNo;
}