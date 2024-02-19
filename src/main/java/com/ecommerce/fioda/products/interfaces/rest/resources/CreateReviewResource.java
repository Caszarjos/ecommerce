package com.ecommerce.fioda.products.interfaces.rest.resources;

public record CreateReviewResource(

        String description,
        int rating
) {
}
