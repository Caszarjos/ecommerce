package com.ecommerce.fioda.products.domain.services;

import com.ecommerce.fioda.products.domain.model.commands.SeedCategoryCommand;

public interface CategoryCommandService {

    void handle(SeedCategoryCommand command);
}
