package com.ssafy.trip.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 목록 조회용 DTO")
public class PostList {

    @Schema(description = "게시글 번호", example = "101")
    private int postNo;

    @Schema(description = "제목", example = "여행 후기")
    private String title;

    @Schema(description = "작성자 이름", example = "김싸피")
    private String writer;

    @Schema(description = "작성일", example = "2025-05-07 14:00:00")
    private String createdAt;

    @Schema(description = "글 유형", example = "GENERAL")
    private String postType;
}
