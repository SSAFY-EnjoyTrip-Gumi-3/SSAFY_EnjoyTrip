package com.ssafy.trip.service;

import java.util.List;
import com.ssafy.trip.model.dto.PlanRequest;
import com.ssafy.trip.model.dto.PlanResponse;

/**
 * 여행 계획(Plan) 관련 서비스 인터페이스.
 * 사용자가 여행 일정을 생성, 조회, 삭제할 수 있도록 지원합니다.
 */
public interface PlanService {

    /**
     * 여행 계획을 생성합니다.
     *
     * @param userNo  계획을 등록할 사용자 번호
     * @param request 계획 제목 및 관광지 목록이 담긴 요청 객체
     * @return 생성된 계획의 고유 번호(planNo)
     */
    int createPlan(int userNo, PlanRequest request);

    /**
     * 특정 사용자의 여행 계획 목록을 조회합니다.
     *
     * @param userNo 조회 대상 사용자 번호
     * @return 사용자의 모든 여행 계획 리스트
     */
    List<PlanResponse> getPlans(int userNo);

    /**
     * 특정 여행 계획의 상세 정보를 조회합니다.
     *
     * @param planNo 조회할 계획의 고유 번호
     * @return 해당 계획의 상세 정보 (제목, 날짜별 관광지 등)
     */
    PlanResponse getPlan(int planNo);

    /**
     * 특정 여행 계획을 삭제합니다.
     * 삭제 권한은 해당 계획의 소유자(userNo)만 가집니다.
     *
     * @param planNo 삭제할 계획의 번호
     * @param userNo 현재 로그인한 사용자 번호
     * @return 삭제 성공 시 1, 실패 시 0
     */
    int deletePlan(int planNo, int userNo);
}
