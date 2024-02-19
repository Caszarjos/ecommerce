package com.ecommerce.fioda.products.domain.model.commands;

import com.ecommerce.fioda.products.domain.model.entities.Category;

public record CreateProductCommand(
        String name,
        Double unit_price,
        String description,
        String color,
        String brand,
        Category category
) {
}
