package com.ecommerce.fioda.products.interfaces.rest.transform;

import com.ecommerce.fioda.products.domain.model.entities.Review;
import com.ecommerce.fioda.products.domain.model.queries.GetProductReviewsQuery;
import com.ecommerce.fioda.products.domain.services.ReviewQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products/{productId}/reviews", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reviews")
public class ProductReviewsController {

    private final ReviewQueryService reviewQueryService;

    public ProductReviewsController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) {
        var getProductReviewsQuery = new GetProductReviewsQuery(productId);
        List<Review> reviews = reviewQueryService.handle(getProductReviewsQuery);

        var reviewsResource
    }

}
