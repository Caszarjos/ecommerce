package com.ecommerce.fioda.products.domain.model.commands;

public record CreateReviewCommand(
        Long productId,
        String description,
        int rating
) {
}
