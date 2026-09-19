package com.kisan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.kisan.models.FarmingType;
import com.kisan.models.UserEntity;
import com.kisan.models.UserRole;


public record UpdateProfileDto(
		String email,
		String firstName,
		String lastName,
		String gender,
		UserRole role,
		FarmingType farmingType,
		String adrLine1,
		String adrLine2,
		String city,
		String state,
		String zipCode
) {}
