package com.gokul.ai.ai_ecommerce.Repository.Cart;

import com.gokul.ai.ai_ecommerce.Entity.CartEntity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}

