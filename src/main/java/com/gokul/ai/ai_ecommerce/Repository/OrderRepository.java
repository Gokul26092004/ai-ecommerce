package com.gokul.ai.ai_ecommerce.Repository;

import com.gokul.ai.ai_ecommerce.Entity.OderEntity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
