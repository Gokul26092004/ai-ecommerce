package com.gokul.ai.ai_ecommerce.Service.ReviewService;

import com.gokul.ai.ai_ecommerce.Dto.ReviewDto.AddReviewDto;
import com.gokul.ai.ai_ecommerce.Entity.Product;
import com.gokul.ai.ai_ecommerce.Entity.ReviewEntity.Review;
import com.gokul.ai.ai_ecommerce.Repository.ProductRepository;
import com.gokul.ai.ai_ecommerce.Repository.ReviewRepository;
import com.gokul.ai.ai_ecommerce.Repository.UserRepository;
import com.gokul.ai.ai_ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Review addReview(AddReviewDto dto,Long userId){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Review review = Review.builder()
                .rating(dto.getRating())
                .comment(dto.getComment())
                .product(product)
                .user(user)
                .build();
        reviewRepository.save(review);
        updateProductRating(product.getId());

        return review;
    }
    public List<Review> getReviews(Long productId){
        return reviewRepository.findByProductId(productId);
    }
    private void updateProductRating(Long productId){
        List<Review> reviews = reviewRepository.findByProductId(productId);

        double avg = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
        Product product = productRepository.findById(productId).orElseThrow();
        product.setRating(avg);
        productRepository.save(product);
    }

}
