package com.ecommerce.fioda.products.application.internal.commandservices;

import com.ecommerce.fioda.products.domain.model.commands.CreateInventoryCommand;
import com.ecommerce.fioda.products.domain.model.entities.Inventory;
import com.ecommerce.fioda.products.domain.services.InventoryCommandService;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Inventory> handle(CreateInventoryCommand command) {

        var product = productRepository.findById(command.productId());

        var inventory = new Inventory(
                command.stock(),
                command.inventory(),
                command.location(),
                product.get()
        );
        return Optional.of(inventory);
    }
}
