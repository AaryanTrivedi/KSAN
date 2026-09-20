package in.ksan.dto;

import in.ksan.models.UserRole;

public record UserRespDTO(
		String firstName,
		String lastName,
		String email,
		String password,
		String gender,
		UserRole role
) {}


