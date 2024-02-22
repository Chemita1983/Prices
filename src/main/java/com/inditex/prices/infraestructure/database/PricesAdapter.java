package com.inditex.prices.infraestructure.database;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import com.inditex.prices.infraestructure.database.mappers.ProductMapper;
import org.springframework.stereotype.Service;


@Service
public class PricesAdapter implements PricesPort {

    private final ProductMapper productMapper;

    private final PricesRepository pricesRepository;

    public PricesAdapter(ProductMapper productMapper, PricesRepository pricesRepository) {
        this.productMapper = productMapper;
        this.pricesRepository = pricesRepository;
    }

    @Override
    public Product getPricesByFilter(ProductQuery priceDTO) {
        PricesVO pricesQueryResult = getPricesQuery().invoke(priceDTO);

        return productMapper.mapToProduct(pricesQueryResult);
    }

    private QueryBuilder getPricesQuery() {
        return pricesRepository::findByPriceDTOWithDates;
    }
}
