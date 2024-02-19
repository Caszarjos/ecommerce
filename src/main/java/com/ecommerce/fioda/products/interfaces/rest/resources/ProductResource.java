package com.ecommerce.fioda.products.interfaces.rest.resources;

import com.ecommerce.fioda.products.domain.model.entities.Category;

public record ProductResource(
        Long id,
        String name,
        Double unit_price,
        String description,
        String color,
        String brand,
        String category
) {
}
