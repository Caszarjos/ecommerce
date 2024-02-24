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
    private Long inventory;
    private String location;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Inventory(Long stock, Long inventory, String location, Product product) {
        this.stock = stock;
        this.inventory = inventory;
        this.location = location;
        this.product = product;
    }
}
