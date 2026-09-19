package com.kisan.dto;

import com.kisan.models.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponseDto(
        double totalAmount,
        OrderStatus status,
        LocalDateTime orderDate,
        String shippingAddress
) {}