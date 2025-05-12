package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trip.model.dto.PlanAttractionRequest;
import com.ssafy.trip.model.dto.PlanInsertParam;
import com.ssafy.trip.model.dto.PlanResponse;

/**
 * 여행 계획(Plan) 관련 데이터베이스 접근 객체 인터페이스.
 */
@Mapper
public interface PlanDao {

    /**
     * 여행 계획을 등록합니다.
     *
     * @param userNo 계획을 등록하는 사용자 번호
     * @param title  계획 제목
     * @return 등록된 행 수 (성공 시 1)
     */
	int insertPlan(PlanInsertParam plan); // planNo는 insert 후 자동 세팅

    /**
     * 특정 계획에 포함된 관광지 일정들을 저장합니다.
     *
     * @param planNo      대상 계획 번호
     * @param attractions 관광지 번호, 날짜, 순서 정보를 담은 리스트
     * @return 등록된 attraction 수
     */
    int insertPlanAttractions(@Param("planNo") int planNo, @Param("list") List<PlanAttractionRequest> attractions);

    /**
     * 사용자의 모든 여행 계획 목록을 조회합니다.
     *
     * @param userNo 사용자 번호
     * @return 해당 사용자의 여행 계획 리스트
     */
    List<PlanResponse> getPlansByUser(int userNo);

    /**
     * 특정 계획 번호에 해당하는 계획 상세 정보를 조회합니다.
     *
     * @param planNo 계획 번호
     * @return 계획 상세 정보, 존재하지 않으면 null
     */
    PlanResponse getPlanByNo(int planNo);

    /**
     * 특정 계획을 삭제합니다.
     *
     * @param planNo 삭제할 계획 번호
     * @return 삭제된 행 수 (성공 시 1)
     */
    int deletePlan(int planNo);
}
