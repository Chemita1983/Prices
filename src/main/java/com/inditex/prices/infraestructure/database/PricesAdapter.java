package com.inditex.prices.infraestructure.database;

import com.inditex.prices.domain.exception.ProductNotFoundException;
import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import com.inditex.prices.infraestructure.database.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


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
        List<PricesVO> pricesQueryResult = getPricesQuery().invoke(priceDTO);

        return getPrices(pricesQueryResult);
    }

    private Product getPrices(List<PricesVO> pricesResult) {
       return pricesResult.stream()
                .max(Comparator.comparing(PricesVO::getPriority))
                .map(productMapper::mapToProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    private QueryBuilder getPricesQuery() {
        return pricesRepository::findByPriceDTOWithDates;
    }
}
