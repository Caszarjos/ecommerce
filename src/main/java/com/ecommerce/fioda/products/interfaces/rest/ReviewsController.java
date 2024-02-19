package com.ecommerce.fioda.products.interfaces.rest;

import com.ecommerce.fioda.products.domain.model.commands.CreateReviewCommand;
import com.ecommerce.fioda.products.domain.model.commands.UpVoteReviewCommand;
import com.ecommerce.fioda.products.domain.services.ReviewCommandService;
import com.ecommerce.fioda.products.interfaces.rest.resources.CreateReviewResource;
import com.ecommerce.fioda.products.interfaces.rest.resources.ReviewResource;
import com.ecommerce.fioda.products.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Reviews Management Endpoints")

public class ReviewsController {

    private final ReviewCommandService reviewCommandService;

    public ReviewsController(ReviewCommandService reviewCommandService) {
        this.reviewCommandService = reviewCommandService;
    }


    @PostMapping("/{productId}")
    public ResponseEntity<ReviewResource> createReview(@PathVariable Long productId, @RequestBody CreateReviewResource createReviewResource) {

        var createReviewCommand = new CreateReviewCommand(
                productId,
                createReviewResource.description(),
                createReviewResource.rating()
        );
        var newReview = reviewCommandService.handle(createReviewCommand);
        if (newReview.isEmpty()) return ResponseEntity.badRequest().build();

        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(newReview.get());
        return new ResponseEntity<>(reviewResource, HttpStatus.CREATED);
    }

    @PostMapping("/upvote/{reviewId}")
    public ResponseEntity<ReviewResource> upVoteReview(@PathVariable Long reviewId) {
        var upvoteReviewCommand = new UpVoteReviewCommand(reviewId);
        var upvoteReview = reviewCommandService.handle(upvoteReviewCommand);
        if (upvoteReview.isEmpty()) return ResponseEntity.badRequest().build();

        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(upvoteReview.get());
        return new ResponseEntity<>(reviewResource, HttpStatus.ACCEPTED);
    }
}
