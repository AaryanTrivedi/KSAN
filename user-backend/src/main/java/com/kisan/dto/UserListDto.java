package com.kisan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kisan.models.FarmingType;
import com.kisan.models.UserRole;

import jakarta.validation.constraints.NotBlank;

public record UserListDto(
		@NotBlank String firstName,
		String lastName,
		String email,
		String mobile,
		String gender,
		boolean status,
		String adrLine1,
		String adrLine2,
		String city,
		String state,
		String zipCode,
		UserRole role,
		FarmingType farmingType
) {}

