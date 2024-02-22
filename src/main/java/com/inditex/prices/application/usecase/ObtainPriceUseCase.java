package com.inditex.prices.application.usecase;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.domain.ports.ObtainPrice;
import com.inditex.prices.domain.ports.PricesPort;

public class ObtainPriceUseCase implements ObtainPrice {

    private final PricesPort pricesPort;

    public ObtainPriceUseCase(PricesPort pricesPort) {
        this.pricesPort = pricesPort;}

   public Product getPriceByFilter(ProductQuery productQuery) {
       return pricesPort.getPricesByFilter(productQuery);
    }
}
