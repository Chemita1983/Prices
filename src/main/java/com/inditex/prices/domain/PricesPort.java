package com.inditex.prices.domain;

import com.inditex.prices.application.inbound.PriceDTO;
import com.inditex.prices.domain.product.Product;

import java.text.ParseException;
import java.util.List;

public interface PricesPort {

    List<Product> getPricesByFilter(PriceDTO price) throws ParseException;
}
