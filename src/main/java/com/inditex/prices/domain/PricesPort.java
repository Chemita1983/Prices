package com.inditex.prices.domain;

import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.domain.price.Price;

import java.text.ParseException;
import java.util.List;

public interface PricesPort {

    List<Price> getPricesByFilter(PriceDTO price) throws ParseException;
}
