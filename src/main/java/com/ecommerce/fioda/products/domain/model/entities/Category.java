package com.ecommerce.fioda.products.domain.model.entities;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.valueobjects.Categories;
import com.ecommerce.fioda.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
public class Category extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    @Getter
    private Categories name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(Categories name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Category toCategoryFromName(String name) {
        return new Category(Categories.valueOf(name));
    }
}
