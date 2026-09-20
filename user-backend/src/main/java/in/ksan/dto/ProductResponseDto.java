package in.ksan.dto;

import in.ksan.models.MetricType;

public record ProductResponseDto(
		String productName,
		String productType,
		int price,
		int totalStock,
		int stockToSell,
		boolean markedForSale,
		MetricType metric,
		String farmingType,
		double landArea
) {}