
package in.ksan.dto;


import in.ksan.models.FarmingType;
import in.ksan.models.MetricType;
import in.ksan.models.ProductType;

public record ProductRequestDto(
		String productName,
		ProductType productType,
		FarmingType farmingType,
		int totalStock,
		MetricType metric,
		double landArea
) {}
