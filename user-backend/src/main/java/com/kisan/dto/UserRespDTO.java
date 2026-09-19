package com.kisan.dto;

import com.kisan.models.UserRole;

public record UserRespDTO(
		String firstName,
		String lastName,
		String email,
		String password,
		String gender,
		UserRole role
) {}


