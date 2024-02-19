package com.ecommerce.fioda.products.interfaces.rest.transform;

import com.ecommerce.fioda.products.domain.model.entities.Review;
import com.ecommerce.fioda.products.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
                entity.getId(),
                entity.getDescription(),
                entity.getRating(),
                entity.getReviewRating()
        );
    }
}
