package com.ssafy.trip.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRequest {

    @NotBlank private String password;
    @Email    private String email;
    @NotBlank private String name;
    @NotBlank private String birthdate;   
    @NotBlank private String gender;  
    @NotBlank private String phonenum;  
}
