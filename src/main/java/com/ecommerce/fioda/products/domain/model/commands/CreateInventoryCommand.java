package com.ecommerce.fioda.products.domain.model.commands;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;

public record CreateInventoryCommand(
        Long stock,
        Long inventory,
        String location,
        Long productId
) {
}
