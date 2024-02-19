package com.ecommerce.fioda.products.domain.services;


import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.commands.CreateProductCommand;
import com.ecommerce.fioda.products.domain.model.commands.DeleteProductCommand;
import com.ecommerce.fioda.products.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}
