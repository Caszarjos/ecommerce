package com.ecommerce.fioda.products.domain.services;

import com.ecommerce.fioda.products.domain.model.aggregates.Product;
import com.ecommerce.fioda.products.domain.model.queries.GetAllProductsQuery;

import java.util.List;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsQuery query);
}
