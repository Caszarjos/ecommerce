package com.ecommerce.fioda.products.domain.model.entities;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Inventory extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private Long stock;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Inventory(Product product, Long stock) {
        this.product = product;
        this.stock = stock;
    }
}
