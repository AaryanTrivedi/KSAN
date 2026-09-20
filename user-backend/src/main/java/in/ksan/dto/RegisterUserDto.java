package in.ksan.dto;

import in.ksan.models.FarmingType;
import in.ksan.models.UserRole;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserDto(
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