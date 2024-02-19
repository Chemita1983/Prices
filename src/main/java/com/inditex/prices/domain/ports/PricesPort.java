package com.inditex.prices.domain.ports;

import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.domain.product.Product;
import com.inditex.prices.domain.product.Products;

import java.text.ParseException;
import java.util.List;

public interface PricesPort {

    Products getPricesByFilter(PriceDTO price) throws ParseException;
}
