package in.ksan.dto;

import java.util.List;

import in.ksan.models.Orders.OrderStatus;

public record OrderDto (List<OrderItemDto> items,double totalAmount,OrderStatus status){}
