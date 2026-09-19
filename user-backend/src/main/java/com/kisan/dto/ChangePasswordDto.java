package com.kisan.dto;

public record ChangePasswordDto(
		String oldPassword,
		String newPassword
) {}