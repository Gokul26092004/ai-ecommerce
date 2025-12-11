package com.gokul.ai.ai_ecommerce.Repository;

import com.gokul.ai.ai_ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
