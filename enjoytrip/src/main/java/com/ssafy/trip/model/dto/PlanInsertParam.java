package com.ssafy.trip.model.dto;

import lombok.Data;

@Data
public class PlanInsertParam {
    private int planNo;       // auto_increment 값
    private int userNo;
    private String title;
}