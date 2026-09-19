package com.kisan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.kisan.models.FarmingType;
import com.kisan.models.UserRole;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
		@NotBlank String firstName,
		String lastName,
		String email,
		String password,
		String mobile,
		String gender,
		String adrLine1,
		String adrLine2,
		String city,
		String state,
		String zipCode,
		UserRole role,
		FarmingType farmingType
) {}