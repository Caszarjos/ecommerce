package com.ecommerce.fioda.products.interfaces.rest.resources;

public record CreateInventoryResource(
        Long stock,
        Long inventory,
        String location,
        Long productId
) {
}
