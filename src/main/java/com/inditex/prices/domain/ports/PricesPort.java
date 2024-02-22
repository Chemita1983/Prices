package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;

public interface PricesPort {
    Product getPricesByFilter(ProductQuery productQuery);
}
