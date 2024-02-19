package com.inditex.prices.infraestructure.adapters;


import com.inditex.prices.infraestructure.model.PriceDto;
import com.inditex.prices.infraestructure.repository.entity.PricesVO;

import java.util.List;

@FunctionalInterface
public interface QueryBuilder {
    List<PricesVO> invoke(PriceDto priceDto);
}
