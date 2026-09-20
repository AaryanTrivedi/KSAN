package in.ksan.dto;

public record CartItemDto(
        Long id,
        String name,
        int quantity,
        double price
) {}