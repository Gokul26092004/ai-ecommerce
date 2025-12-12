package com.gokul.ai.ai_ecommerce.Service.ReviewService;

import com.gokul.ai.ai_ecommerce.Dto.ReviewDto.AddReviewDto;
import com.gokul.ai.ai_ecommerce.Entity.ReviewEntity.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(AddReviewDto dto,Long userId);
    List<Review> getReviews(Long productId);
}
