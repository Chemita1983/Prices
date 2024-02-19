package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.product.Products;
import com.inditex.prices.infraestructure.model.PriceDto;

import java.text.ParseException;

public interface PricesPort {

    Products getPricesByFilter(PriceDto price) throws ParseException;
}
