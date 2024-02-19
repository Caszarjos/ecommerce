package com.ecommerce.fioda.products.application.internal.commandservices;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.commands.CreateProductCommand;
import com.ecommerce.fioda.products.domain.model.commands.DeleteProductCommand;
import com.ecommerce.fioda.products.domain.model.commands.UpdateProductCommand;
import com.ecommerce.fioda.products.domain.services.ProductCommandService;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.CategoryRepository;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {

        if(productRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Product with same name already exists");
        }
        if(!categoryRepository.existsByName(command.category().getName())) {
            throw new IllegalArgumentException("Category with name " + command.category().getName() + " doesn't exists");
        }
        var category = categoryRepository.findByName(command.category().getName());

        var product = new Product(
                command.name(),
                command.unit_price(),
                command.description(),
                command.color(),
                command.brand(),
                category.get()
        );
        productRepository.save(product);

        return Optional.of(product);
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        if(!productRepository.existsById(command.productId())) {
            throw new IllegalArgumentException("Product does not exists");
        }

        var category = categoryRepository.findByName(command.category().getName());
        var productToUpdate = productRepository.findById(command.productId()).get();
        var updatedProduct = productRepository.save(productToUpdate.updateInformation(
                command.name(),
                command.unit_price(),
                command.description(),
                command.color(),
                command.brand(),
                category.get()
        ));
        return Optional.of(updatedProduct);
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if(!productRepository.existsById(command.productId())) {
            throw new IllegalArgumentException("Product does not exist");
        }
        productRepository.deleteById(command.productId());
    }
}
