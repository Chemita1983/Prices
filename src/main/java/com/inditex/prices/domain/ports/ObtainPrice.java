package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;

import java.util.List;

public interface ObtainPrice {

    List<Product> getPriceByFilter(ProductQuery productQuery);
}
