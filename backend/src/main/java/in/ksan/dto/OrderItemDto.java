package in.ksan.dto;

public record OrderItemDto(
        Long productId,
        int quantity,
        double price
) {}