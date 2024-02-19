package com.inditex.prices.infraestructure.outbound;


import com.inditex.prices.infraestructure.inbound.model.PriceDTO;
import com.inditex.prices.infraestructure.outbound.repository.entity.PricesVO;

import java.util.List;

@FunctionalInterface
public interface QueryBuilder {
    List<PricesVO> invoke(PriceDTO priceDTO);
}
