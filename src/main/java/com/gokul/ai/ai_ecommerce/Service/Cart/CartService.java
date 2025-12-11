package com.gokul.ai.ai_ecommerce.Service.Cart;


import com.gokul.ai.ai_ecommerce.Entity.CartEntity.Cart;
import com.gokul.ai.ai_ecommerce.Entity.CartEntity.CartItem;
import com.gokul.ai.ai_ecommerce.Repository.Cart.CartItemRepository;
import com.gokul.ai.ai_ecommerce.Repository.Cart.CartRepository;
import com.gokul.ai.ai_ecommerce.model.*;
import com.gokul.ai.ai_ecommerce.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder().userId(userId).build()
                ));
    }

    public Cart addToCart(Long userId, Long productId, int qty) {
        Cart cart = getCart(userId);

        // Check if already exists
        Optional<CartItem> existing = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + qty);
        } else {
            CartItem item = CartItem.builder()
                    .cart(cart)
                    .productId(productId)
                    .quantity(qty)
                    .build();
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public Cart updateQuantity(Long itemId, int qty) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(qty);
        cartItemRepository.save(item);
        return item.getCart();
    }

    public Cart removeFromCart(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Cart cart = item.getCart();
        cart.getItems().remove(item);

        cartItemRepository.delete(item);

        return cartRepository.save(cart);
    }
}

