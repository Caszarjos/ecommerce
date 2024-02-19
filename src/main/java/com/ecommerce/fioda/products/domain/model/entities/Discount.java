package com.ecommerce.fioda.products.domain.model.entities;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
public class Discount extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String name;
    private String description;
    private Float discount_percent;
    private boolean active;
    @ManyToMany
    @JoinTable(
            name = "product_discounts",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Discount(
            String name,
            String description,
            Float discount_percent,
            boolean active,
            List<Product> products)
    {
        this.name = name;
        this.description = description;
        this.discount_percent = discount_percent;
        this.active = active;
        this.products = products;
    }
}
