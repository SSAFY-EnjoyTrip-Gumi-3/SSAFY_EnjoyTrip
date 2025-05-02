package com.ssafy.trip.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

	@NotBlank
    private String id;

	@NotBlank
    private String password;
}
