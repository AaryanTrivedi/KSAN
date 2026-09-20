package in.ksan.dto;

import in.ksan.models.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponseDto(
        double totalAmount,
        OrderStatus status,
        LocalDateTime orderDate,
        String shippingAddress
) {}