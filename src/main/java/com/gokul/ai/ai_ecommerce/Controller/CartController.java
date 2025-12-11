package com.gokul.ai.ai_ecommerce.Controller;


import com.gokul.ai.ai_ecommerce.Entity.CartEntity.Cart;
import com.gokul.ai.ai_ecommerce.Service.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam int qty) {
        return cartService.addToCart(userId, productId, qty);
    }

    @PutMapping("/update/{itemId}")
    public Cart updateQuantity(@PathVariable Long itemId,
                               @RequestParam int qty) {
        return cartService.updateQuantity(itemId, qty);
    }

    @DeleteMapping("/remove/{itemId}")
    public Cart removeItem(@PathVariable Long itemId) {
        return cartService.removeFromCart(itemId);
    }
}
