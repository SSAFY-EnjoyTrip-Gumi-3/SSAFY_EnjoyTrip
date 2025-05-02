package com.ssafy.trip.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private int userNo;
    private String id;
    private String email;
    private String name;

}
