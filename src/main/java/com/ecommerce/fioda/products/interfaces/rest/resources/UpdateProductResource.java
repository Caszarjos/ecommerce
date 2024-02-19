package com.ecommerce.fioda.products.interfaces.rest.resources;

public record UpdateProductResource(
        String name,
        Double unit_price,
        String description,
        String color,
        String brand,
        String category
) {
}
