package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.model.dto.PlanRequest;
import com.ssafy.trip.model.dto.PlanResponse;
import com.ssafy.trip.security.AuthContext;
import com.ssafy.trip.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
@Tag(name = "Plan", description = "여행 계획 작성/조회/삭제 API")
public class PlanController implements RestControllerHelper {

    private final PlanService planService;
    private final AuthContext authContext;

    @Operation(summary = "내 여행 계획 목록 조회", description = "로그인 사용자의 전체 여행 계획 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<?> list(HttpSession session) {
        int userNo = authContext.getCurrentUserNo(session);
        List<PlanResponse> plans = planService.getPlans(userNo);
        return handleSuccess(plans);
    }

    @Operation(summary = "여행 계획 상세 조회", description = "여행 계획 ID로 상세 정보를 조회합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 계획")
    })
    @GetMapping("/{planNo}")
    public ResponseEntity<?> detail(@PathVariable int planNo) {
        PlanResponse plan = planService.getPlan(planNo);
        return handleSuccess(plan);
    }

    @Operation(summary = "여행 계획 생성", description = "새로운 여행 계획을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "생성 완료")
    @PostMapping
    public ResponseEntity<?> write(@Valid @RequestBody PlanRequest request, HttpSession session) {
        int userNo = authContext.getCurrentUserNo(session);
        int planNo = planService.createPlan(userNo, request);
        return handleSuccess("여행 계획이 생성되었습니다. (ID: " + planNo + ")", HttpStatus.CREATED);
    }

    @Operation(summary = "여행 계획 삭제", description = "본인의 여행 계획을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "삭제 완료"),
        @ApiResponse(responseCode = "404", description = "존재하지 않거나 이미 삭제된 계획")
    })
    @DeleteMapping("/{planNo}")
    public ResponseEntity<?> delete(@PathVariable int planNo, HttpSession session) {
        int userNo = authContext.getCurrentUserNo(session);
        planService.deletePlan(planNo, userNo);
        return handleSuccess("여행 계획이 삭제되었습니다.");
    }
}
