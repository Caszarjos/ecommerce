package com.ecommerce.fioda.products.interfaces.rest;


import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.commands.CreateProductCommand;
import com.ecommerce.fioda.products.domain.model.commands.DeleteProductCommand;
import com.ecommerce.fioda.products.domain.model.commands.UpdateProductCommand;
import com.ecommerce.fioda.products.domain.model.entities.Category;
import com.ecommerce.fioda.products.domain.model.queries.GetAllProductsQuery;
import com.ecommerce.fioda.products.domain.services.ProductCommandService;
import com.ecommerce.fioda.products.domain.services.ProductQueryService;
import com.ecommerce.fioda.products.interfaces.rest.resources.CreateProductResource;
import com.ecommerce.fioda.products.interfaces.rest.resources.ProductResource;
import com.ecommerce.fioda.products.interfaces.rest.resources.UpdateProductResource;
import com.ecommerce.fioda.products.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Products Management Endpoints")

public class ProductsController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductsController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {

        var createProductCommand = new CreateProductCommand(
                createProductResource.name(),
                createProductResource.unit_price(),
                createProductResource.description(),
                createProductResource.color(),
                createProductResource.brand(),
                Category.toCategoryFromName(createProductResource.category())
                );

        var newProduct = productCommandService.handle(createProductCommand);
        if (newProduct.isEmpty()) return ResponseEntity.badRequest().build();

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(newProduct.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource updateProductResource){
        var updateProductCommand = new UpdateProductCommand(
                productId,
                updateProductResource.name(),
                updateProductResource.unit_price(),
                updateProductResource.description(),
                updateProductResource.color(),
                updateProductResource.brand(),
                Category.toCategoryFromName(updateProductResource.category())
        );

        var updatedProduct = productCommandService.handle(updateProductCommand);
        if (updatedProduct.isEmpty()) return ResponseEntity.badRequest().build();

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(updatedProduct.get());
        return new ResponseEntity<>(productResource, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        var deleteCourseCommand = new DeleteProductCommand(productId);
        productCommandService.handle(deleteCourseCommand);
        return ResponseEntity.ok("Product with given id successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductsCommand = new GetAllProductsQuery();
        List<Product> products = productQueryService.handle(getAllProductsCommand);

        var productResources = products
                .stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(productResources);
    }
}
