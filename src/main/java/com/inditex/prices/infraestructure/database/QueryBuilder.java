package com.inditex.prices.infraestructure.database;


import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.infraestructure.database.entity.PricesVO;

import java.util.List;

@FunctionalInterface
public interface QueryBuilder {
    List<PricesVO> invoke(ProductQuery productQuery);
}
