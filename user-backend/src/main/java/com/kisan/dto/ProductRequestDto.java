
package com.kisan.dto;


import com.kisan.models.FarmingType;
import com.kisan.models.MetricType;
import com.kisan.models.ProductType;

public record ProductRequestDto(
		String productName,
		ProductType productType,
		FarmingType farmingType,
		int totalStock,
		MetricType metric,
		double landArea
) {}
