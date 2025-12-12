package com.gokul.ai.ai_ecommerce.Dto.PlaceOrderDto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderDto {
    private List<OrderItemDto> items;
}
