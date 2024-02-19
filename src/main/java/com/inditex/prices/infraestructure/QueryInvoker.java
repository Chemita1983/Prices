package com.inditex.prices.infraestructure;


import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.infraestructure.entity.PricesVO;

import java.util.List;

@FunctionalInterface
public interface QueryInvoker {
    List<PricesVO> invoke(PriceDTO priceDTO);
}
