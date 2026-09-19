package com.kisan.dto;

public record OrderItemDto(
        Long productId,
        int quantity,
        double price
) {}