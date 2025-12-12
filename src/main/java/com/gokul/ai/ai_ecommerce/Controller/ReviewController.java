package com.gokul.ai.ai_ecommerce.Controller;

import com.gokul.ai.ai_ecommerce.Dto.ReviewDto.AddReviewDto;
import com.gokul.ai.ai_ecommerce.Entity.ReviewEntity.Review;
import com.gokul.ai.ai_ecommerce.Security.JwtFilter;
import com.gokul.ai.ai_ecommerce.Security.JwtUtil;
import com.gokul.ai.ai_ecommerce.Service.ReviewService.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final JwtUtil jwtUtil;

    @PostMapping("/add")
    public Review addReview(
            @RequestBody AddReviewDto dto,
            @RequestHeader("Authorization") String authHeader
            ){
        Long userId = jwtUtil.extractUserId(authHeader.substring(7));
        return reviewService.addReview(dto,userId);
    }
    @GetMapping("/{productId}")
    public List<Review> getReviews(@PathVariable Long productId){
        return  reviewService.getReviews(productId);
    }
}
