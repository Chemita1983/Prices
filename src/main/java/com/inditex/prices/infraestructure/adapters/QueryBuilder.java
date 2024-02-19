package com.inditex.prices.infraestructure.adapters;


import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.infraestructure.repository.entity.PricesVO;

import java.util.List;

@FunctionalInterface
public interface QueryBuilder {
    List<PricesVO> invoke(PriceDTO priceDTO);
}
