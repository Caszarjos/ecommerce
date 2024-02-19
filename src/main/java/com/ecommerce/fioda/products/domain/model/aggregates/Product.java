package com.ecommerce.fioda.products.domain.model.aggregates;

import com.ecommerce.fioda.products.domain.model.entities.Category;
import com.ecommerce.fioda.products.domain.model.entities.Discount;
import com.ecommerce.fioda.products.domain.model.entities.Review;
import com.ecommerce.fioda.products.domain.model.valueobjects.SKU;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product extends AbstractAggregateRoot<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double unit_price;

    private Float rating;

    private String description;

    @Embedded
    private SKU sku;

    private String color;

    private String brand;

    private String size;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date created_at;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private List<Discount> discounts;

    public Product() {
        this.reviews = new ArrayList<>();
        this.discounts = new ArrayList<>();
        this.rating = 0f;
    }

    public Product(
            String name,
            Double unit_price,
            String description,
            String color,
            String brand,
            Category category
    ) {
        this();
        this.name = name;
        this.unit_price = unit_price;
        this.description = description;
        this.color = color;
        this.brand = brand;
        this.category = category;
        this.sku = new SKU(brand, color, name);
    }

    public Product updateInformation(
            String name,
            Double unit_price,
            String description,
            String color,
            String brand,
            Category category
    ){
        this.name = name;
        this.unit_price = unit_price;
        this.description = description;
        this.color = color;
        this.brand = brand;
        this.category = category;
        this.sku = new SKU(brand, color, name);
        return this;
    }

}
