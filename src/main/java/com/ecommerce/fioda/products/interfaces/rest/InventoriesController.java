package com.ecommerce.fioda.products.interfaces.rest;

import com.ecommerce.fioda.products.domain.model.commands.CreateInventoryCommand;
import com.ecommerce.fioda.products.domain.model.entities.Inventory;
import com.ecommerce.fioda.products.domain.services.InventoryCommandService;
import com.ecommerce.fioda.products.domain.services.InventoryQueryService;
import com.ecommerce.fioda.products.interfaces.rest.resources.CreateInventoryResource;
import com.ecommerce.fioda.products.interfaces.rest.resources.CreateProductResource;
import com.ecommerce.fioda.products.interfaces.rest.resources.ProductResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Inventories", description = "Inventories Management Endpoints")
public class InventoriesController {

    private final InventoryCommandService inventoryCommandService;
    private final InventoryQueryService inventoryQueryService;

    public InventoriesController(InventoryCommandService inventoryCommandService, InventoryQueryService inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody CreateInventoryResource resource) {

        var createInventoryCommand = new CreateInventoryCommand(
                resource.stock(),
                resource.inventory(),
                resource.location(),
                resource.productId()
        );

        var newInventory = inventoryCommandService.handle(createInventoryCommand);
        if (newInventory.isEmpty()) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(newInventory.get(), HttpStatus.CREATED);
    }

}
