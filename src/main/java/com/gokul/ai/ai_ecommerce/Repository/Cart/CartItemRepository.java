package com.gokul.ai.ai_ecommerce.Repository.Cart;


import com.gokul.ai.ai_ecommerce.Entity.CartEntity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
