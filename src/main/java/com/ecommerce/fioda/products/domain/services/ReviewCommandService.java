package com.ecommerce.fioda.products.domain.services;

import com.ecommerce.fioda.products.domain.model.commands.CreateReviewCommand;
import com.ecommerce.fioda.products.domain.model.commands.UpVoteReviewCommand;
import com.ecommerce.fioda.products.domain.model.entities.Review;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
    Optional<Review> handle(UpVoteReviewCommand command);
}
