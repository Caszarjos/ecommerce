package com.ecommerce.fioda.products.application.internal.eventhandlers;

import com.ecommerce.fioda.products.domain.model.commands.SeedCategoryCommand;
import com.ecommerce.fioda.products.domain.services.CategoryCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApplicationReadyEventHandler {
    private final CategoryCommandService categoryCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    public ApplicationReadyEventHandler(CategoryCommandService categoryCommandService) {
        this.categoryCommandService = categoryCommandService;
    }
    
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var name = event.getApplicationContext().getId();
        LOGGER.info("Starting to seed roles for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        var seedCategoryCommand = new SeedCategoryCommand();
        categoryCommandService.handle(seedCategoryCommand);
        LOGGER.info("Categories seeded successfully for {} at {}", name, new Timestamp(System.currentTimeMillis()));
    }
}
