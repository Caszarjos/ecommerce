package com.ecommerce.fioda.products.domain.model.entities;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Review extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String description;

    private int rating;

    private int reviewRating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review(){
        reviewRating = 0;
    }
    public Review(String description, int rating, Product product) {
        this();
        this.description = description;
        this.rating = rating;
        this.product = product;
    }

    public Review upvoteReview() {
        this.reviewRating += 1;
        return this;
    }
}
