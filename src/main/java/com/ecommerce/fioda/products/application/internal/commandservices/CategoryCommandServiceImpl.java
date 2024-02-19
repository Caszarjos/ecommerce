package com.ecommerce.fioda.products.application.internal.commandservices;

import com.ecommerce.fioda.products.domain.model.commands.SeedCategoryCommand;
import com.ecommerce.fioda.products.domain.model.entities.Category;
import com.ecommerce.fioda.products.domain.model.valueobjects.Categories;
import com.ecommerce.fioda.products.domain.services.CategoryCommandService;
import com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;

    public CategoryCommandServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void handle(SeedCategoryCommand command) {
        Arrays.stream(Categories.values()).forEach(category -> {
            if(!categoryRepository.existsByName(category)) categoryRepository.save(new Category(Categories.valueOf(category.name())));
        });
    }
}
