package com.ecommerce.fioda.products.infrastructure.persistence.jpa.repositories;

import com.ecommerce.fioda.products.domain.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}