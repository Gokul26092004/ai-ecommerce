package com.gokul.ai.ai_ecommerce.Repository;

import com.gokul.ai.ai_ecommerce.Entity.ReviewEntity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProductId(Long productId);
}
