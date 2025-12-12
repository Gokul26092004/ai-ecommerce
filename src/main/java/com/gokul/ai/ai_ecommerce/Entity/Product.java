package com.gokul.ai.ai_ecommerce.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    private double rating = 0.0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
