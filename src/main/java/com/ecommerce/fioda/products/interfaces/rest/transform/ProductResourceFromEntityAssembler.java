package com.ecommerce.fioda.products.interfaces.rest.transform;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(
                entity.getId(),
                entity.getName(),
                entity.getUnit_price(),
                entity.getDescription(),
                entity.getColor(),
                entity.getBrand(),
                entity.getCategory().getStringName()
        );
    }
}
