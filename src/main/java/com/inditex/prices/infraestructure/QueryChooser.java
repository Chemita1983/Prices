package com.inditex.prices.infraestructure;


import com.inditex.prices.application.inbound.PriceDTO;
import com.inditex.prices.infraestructure.entity.PricesVO;

import java.util.List;

@FunctionalInterface
public interface QueryChooser {
    List<PricesVO> invoke(PriceDTO priceDTO);
}
