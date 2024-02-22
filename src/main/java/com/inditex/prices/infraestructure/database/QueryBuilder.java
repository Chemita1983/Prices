package com.inditex.prices.infraestructure.database;


import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.infraestructure.database.entity.PricesVO;

@FunctionalInterface
public interface QueryBuilder {
    PricesVO invoke(ProductQuery productQuery);
}
