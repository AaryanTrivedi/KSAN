package com.kisan.dto;

import java.util.List;

import com.kisan.models.OrderItem;
import com.kisan.models.Orders.OrderStatus;

public record OrderDto (List<OrderItemDto> items,double totalAmount,OrderStatus status){}
