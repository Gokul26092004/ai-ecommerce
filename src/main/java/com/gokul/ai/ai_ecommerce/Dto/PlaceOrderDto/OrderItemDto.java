package com.gokul.ai.ai_ecommerce.Dto.PlaceOrderDto;

import lombok.Data;

@Data
public class OrderItemDto{
    private Long productId;
    private int quantity;
}
