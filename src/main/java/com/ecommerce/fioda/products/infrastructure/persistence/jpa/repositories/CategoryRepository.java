package com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories;

import com.ecommerce.fioda.products.domain.model.entities.Category;
import com.ecommerce.fioda.products.domain.model.valueobjects.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(Categories name);
    Optional<Category> findByName(Categories name);
}
