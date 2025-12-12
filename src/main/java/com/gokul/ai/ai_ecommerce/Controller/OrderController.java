package com.gokul.ai.ai_ecommerce.Controller;

import com.gokul.ai.ai_ecommerce.Dto.PlaceOrderDto.PlaceOrderDto;
import com.gokul.ai.ai_ecommerce.Entity.OderEntity.OrderStatus;
import com.gokul.ai.ai_ecommerce.Security.JwtUtil;
import com.gokul.ai.ai_ecommerce.Service.OrderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(
            @RequestBody PlaceOrderDto dto,
            @RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(orderService.placeOrder(userId, dto));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<?> getOrderHistory(
            @RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(orderService.getOrderByUser(userId));
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status,
            @RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
}
