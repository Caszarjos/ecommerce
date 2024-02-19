package com.ecommerce.fioda.products.interfaces.rest.resources;

public record ReviewResource(
        Long productId,
        String description,
        int rating,
        int reviewRating
) {
}
