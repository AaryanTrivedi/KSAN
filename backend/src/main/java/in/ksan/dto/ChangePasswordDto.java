package in.ksan.dto;

public record ChangePasswordDto(
		String oldPassword,
		String newPassword
) {}