package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;

import java.util.List;

public interface PricesPort {

    List<Product> getPricesByFilter(ProductQuery productQuery);
}
