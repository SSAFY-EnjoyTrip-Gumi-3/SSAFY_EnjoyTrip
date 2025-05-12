package com.ssafy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.RecordNotFoundException;
import com.ssafy.trip.model.dao.PlanDao;
import com.ssafy.trip.model.dto.PlanAttractionRequest;
import com.ssafy.trip.model.dto.PlanInsertParam;
import com.ssafy.trip.model.dto.PlanRequest;
import com.ssafy.trip.model.dto.PlanResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanDao pDao;

    @Override
    @Transactional
    public int createPlan(int userNo, PlanRequest request) {
        // 1. planInsertParam 객체 준비
        PlanInsertParam planParam = new PlanInsertParam();
        planParam.setUserNo(userNo);
        planParam.setTitle(request.getTitle());

        // 2. insert → planNo 자동 세팅됨
        pDao.insertPlan(planParam);
        int planNo = planParam.getPlanNo();

        // 3. 관광지 일정 저장
        List<PlanAttractionRequest> attractions = request.getAttractions();
        if (attractions != null && !attractions.isEmpty()) {
            pDao.insertPlanAttractions(planNo, attractions);
        }

        return planNo;
    }


    @Override
    public List<PlanResponse> getPlans(int userNo) {
        return pDao.getPlansByUser(userNo);
    }

    @Override
    public PlanResponse getPlan(int planNo) {
        PlanResponse plan = pDao.getPlanByNo(planNo);
        if (plan == null) {
            throw new RecordNotFoundException("해당 여행 계획을 찾을 수 없습니다.");
        }
        return plan;
    }

    @Override
    @Transactional
    public int deletePlan(int planNo, int userNo) {
        PlanResponse plan = pDao.getPlanByNo(planNo);
        if (plan == null) {
            throw new RecordNotFoundException("해당 여행 계획을 찾을 수 없습니다.");
        }
        // TODO: 필요하면 userNo 비교 로직 추가 (권한 확인)
        return pDao.deletePlan(planNo);
    }
}
