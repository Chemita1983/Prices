package com.inditex.prices.infraestructure.database;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infraestructure.api.mappers.ProductMapper;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PricesAdapter implements PricesPort {

    private final ProductMapper productMapper;

    private final PricesRepository pricesRepository;

    public PricesAdapter(ProductMapper productMapper, PricesRepository pricesRepository) {
        this.productMapper = productMapper;
        this.pricesRepository = pricesRepository;
    }

    @Override
    public List<Product> getPricesByFilter(ProductQuery priceDTO) {
        List<PricesVO> pricesQueryResult = getPricesQuery().invoke(priceDTO);

        return getPrices(pricesQueryResult);
    }

    private List<Product> getPrices(List<PricesVO> pricesResult) {
        List<PricesVO> prices = pricesResult.stream()
                .filter(price -> price.getPriority().equals(getPriceResultMaxPriority(pricesResult)))
                .collect(Collectors.toList());

        return productMapper.mapToProducts(prices);
    }

    private Integer getPriceResultMaxPriority(List<PricesVO> priceResult) {
        return Collections.max(priceResult.stream().map(PricesVO::getPriority).collect(Collectors.toList()));
    }

    private QueryBuilder getPricesQuery() {
        return pricesRepository::findByPriceDTOWithDates;
    }
}
