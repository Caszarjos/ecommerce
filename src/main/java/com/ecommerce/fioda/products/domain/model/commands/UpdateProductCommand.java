package com.ecommerce.fioda.products.domain.model.commands;

import com.ecommerce.fioda.products.domain.model.entities.Category;

public record UpdateProductCommand(
        Long productId,
        String name,
        Double unit_price,
        String description,
        String color,
        String brand,
        Category category
) {
}
