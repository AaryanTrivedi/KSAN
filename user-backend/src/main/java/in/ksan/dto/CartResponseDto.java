package in.ksan.dto;

import java.util.List;

public record CartResponseDto(
		double totalPrice,
		List<CartItemDto> items
) {}