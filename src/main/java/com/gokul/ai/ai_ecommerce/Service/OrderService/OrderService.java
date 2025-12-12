package com.gokul.ai.ai_ecommerce.Service.OrderService;

import com.gokul.ai.ai_ecommerce.Dto.PlaceOrderDto.OrderItemDto;
import com.gokul.ai.ai_ecommerce.Dto.PlaceOrderDto.PlaceOrderDto;
import com.gokul.ai.ai_ecommerce.Entity.OderEntity.Order;
import com.gokul.ai.ai_ecommerce.Entity.OderEntity.OrderItem;
import com.gokul.ai.ai_ecommerce.Entity.OderEntity.OrderStatus;
import com.gokul.ai.ai_ecommerce.Entity.Product;
import com.gokul.ai.ai_ecommerce.Repository.OrderRepository;
import com.gokul.ai.ai_ecommerce.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(Long userId, PlaceOrderDto dto) {

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new RuntimeException("Order cannot be empty");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrderItemDto item : dto.getItems()) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItem.setOrder(order);
            orderItems.add(orderItem);

            total += (product.getPrice() * item.getQuantity());
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    public List<Order> getOrderByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
