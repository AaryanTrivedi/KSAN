package com.kisan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthorizationRequest (
	@NotBlank(message = "Email cannot be null or blank")
	@Email(message = "Invalid Email Format")
	String email,
	@NotBlank
	String password
){}
