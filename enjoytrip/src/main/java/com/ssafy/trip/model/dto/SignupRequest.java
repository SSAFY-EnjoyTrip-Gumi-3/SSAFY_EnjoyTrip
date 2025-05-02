package com.ssafy.trip.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {

    @NotBlank private String id;
    @NotBlank private String password;
    @Email    private String email;
    @NotBlank private String name;
    private String birthdate;   
    private String gender;  
    private String phonenum;  
}
