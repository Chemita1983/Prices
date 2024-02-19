package com.inditex.prices.domain.ports;

import com.inditex.prices.infraestructure.inbound.model.PriceDTO;
import com.inditex.prices.domain.model.Products;

public interface PricesPort {

    Products getPricesByFilter(PriceDTO priceDTO);
}
