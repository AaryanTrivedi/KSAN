package in.ksan.dto;

import in.ksan.models.FarmingType;
import in.ksan.models.UserRole;

public record UserResponseDto(
		Long id,
		String firstName,
		String lastName,
		String email,
		String password,
		String gender,
		String adrLine1,
		String adrLine2,
		String city,
		String state,
		String zipCode,
		UserRole role,
		FarmingType type
) {}