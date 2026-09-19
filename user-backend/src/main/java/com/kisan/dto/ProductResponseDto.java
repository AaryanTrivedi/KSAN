package com.kisan.dto;

import com.kisan.models.FarmingType;
import com.kisan.models.MetricType;
import com.kisan.models.ProductType;

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