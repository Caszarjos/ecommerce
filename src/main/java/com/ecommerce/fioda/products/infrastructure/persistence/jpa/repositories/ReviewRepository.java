package com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByProduct(Product product);

    List<Review> findAllByProductId(Long productId);
}
