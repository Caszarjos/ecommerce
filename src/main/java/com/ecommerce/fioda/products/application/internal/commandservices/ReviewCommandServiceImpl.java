package com.ecommerce.fioda.products.application.internal.commandservices;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.commands.CreateReviewCommand;
import com.ecommerce.fioda.products.domain.model.commands.UpVoteReviewCommand;
import com.ecommerce.fioda.products.domain.model.entities.Review;
import com.ecommerce.fioda.products.domain.services.ReviewCommandService;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewCommandServiceImpl(
            ReviewRepository reviewRepository,
            ProductRepository productRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Review> handle(CreateReviewCommand command) {

        if ( !productRepository.existsById(command.productId()) )
            throw new IllegalArgumentException("Product with id doesn't exists");

        var selectedProduct = productRepository.findById(command.productId());
        var review = new Review(
                command.description(),
                command.rating(),
                selectedProduct.get()
        );
        reviewRepository.save(review);

        return Optional.of(review);
    }

    @Override
    public Optional<Review> handle(UpVoteReviewCommand command) {

        if( !reviewRepository.existsById(command.reviewId()) )
            throw new IllegalArgumentException("Review with id doesn't exists");

        var selectedReview = reviewRepository.findById(command.reviewId());
        reviewRepository.save(selectedReview.get().upvoteReview());

        return selectedReview;
    }
}
