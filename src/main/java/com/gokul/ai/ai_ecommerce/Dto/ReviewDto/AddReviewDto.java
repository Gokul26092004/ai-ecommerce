package com.gokul.ai.ai_ecommerce.Dto.ReviewDto;

import lombok.Data;

@Data
public class AddReviewDto {
    private Long productId;
    private int rating;
    private String comment;
}
