package in.ksan.dto;

import in.ksan.models.FarmingType;
import in.ksan.models.UserRole;


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
