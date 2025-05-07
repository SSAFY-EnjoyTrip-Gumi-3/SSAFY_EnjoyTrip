package com.ssafy.trip.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@Schema(description = "게시글 정보 DTO")
public class Post {

    @Schema(description = "게시글 번호 (PK)", example = "1")
    private int postNo;

    @Schema(description = "작성자 유저 번호 (FK)", example = "5")
    private int userId;

    @Schema(description = "작성자 이름 (조인된 결과용)", example = "김싸피")
    private String authorName;

    @Schema(description = "게시글 제목", example = "제주도 여행 후기")
    private String title;

    @Schema(description = "게시글 내용", example = "정말 재밌는 여행이었어요.")
    private String content;

    @Schema(description = "게시글 타입 (GENERAL | NOTICE)", example = "GENERAL")
    private String postType;

    @Schema(description = "논리 삭제 여부", example = "false")
    private boolean isDeleted;

    @Schema(description = "작성 일시 (yyyy-MM-dd HH:mm:ss)", example = "2025-05-07 14:30:00")
    private String createdAt;

    @Schema(description = "수정 일시 (yyyy-MM-dd HH:mm:ss)", example = "2025-05-07 15:00:00")
    private String updatedAt;

    @Schema(description = "댓글 리스트 (글 상세 조회 시 포함)")
    private List<CommentResponse> comments;
}
