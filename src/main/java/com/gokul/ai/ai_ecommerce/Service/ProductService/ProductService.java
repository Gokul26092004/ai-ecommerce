package com.gokul.ai.ai_ecommerce.Service.ProductService;

import com.gokul.ai.ai_ecommerce.Entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProduct(Long id);
    List<Product> getAllProducts();
}

