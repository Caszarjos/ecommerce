package com.ecommerce.fioda.products.domain.services;

import com.ecommerce.fioda.products.domain.model.entities.Review;
import com.ecommerce.fioda.products.domain.model.queries.GetProductReviewsQuery;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetProductReviewsQuery query);
}
