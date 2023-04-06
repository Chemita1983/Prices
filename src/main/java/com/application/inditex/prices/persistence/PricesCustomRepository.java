package com.application.inditex.prices.persistence;


import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.PricesVO;

import java.util.List;

/**
 * Persistence class for retrieving prices
 *
 * @author chema
 */

public interface PricesCustomRepository {

    List<PricesVO> getPriceByFilter(Price inputPrice);
}

