package com.ecommerce.fioda.products.domain.services;

import com.ecommerce.fioda.products.domain.model.commands.CreateInventoryCommand;
import com.ecommerce.fioda.products.domain.model.entities.Inventory;

import java.util.Optional;

public interface InventoryCommandService {
    Optional<Inventory> handle(CreateInventoryCommand command);
}
