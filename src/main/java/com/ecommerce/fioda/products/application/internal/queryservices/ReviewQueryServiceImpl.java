package com.ecommerce.fioda.products.application.internal.queryservices;

import com.ecommerce.fioda.products.domain.model.entities.Review;
import com.ecommerce.fioda.products.domain.model.queries.GetProductReviewsQuery;
import com.ecommerce.fioda.products.domain.services.ReviewQueryService;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetProductReviewsQuery query) {
        return reviewRepository.findAllByProductId(query.productId());
    }
}
