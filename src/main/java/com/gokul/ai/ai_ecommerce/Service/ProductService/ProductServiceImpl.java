package com.gokul.ai.ai_ecommerce.Service.ProductService;


import com.gokul.ai.ai_ecommerce.Entity.Product;
import com.gokul.ai.ai_ecommerce.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        return repository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
