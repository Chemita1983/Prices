package com.inditex.prices.infraestructure.outbound;

import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.domain.model.Products;
import com.inditex.prices.infraestructure.mappers.ProductMapper;
import com.inditex.prices.infraestructure.inbound.model.PriceDTO;
import com.inditex.prices.infraestructure.outbound.validation.ProductValidator;
import com.inditex.prices.infraestructure.outbound.repository.PricesRepository;
import com.inditex.prices.infraestructure.outbound.repository.entity.PricesVO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PricesH2Adapter implements PricesPort {

    private final ProductMapper productMapper;

    private final ProductValidator productValidator;

    private final PricesRepository pricesRepository;

    public PricesH2Adapter(ProductMapper productMapper, ProductValidator productValidator, PricesRepository pricesRepository) {
        this.productMapper = productMapper;
        this.productValidator = productValidator;
        this.pricesRepository = pricesRepository;
    }

    @Override
    public Products getPricesByFilter(PriceDTO priceDTO) {
        productValidator.validInputPrice(priceDTO);

        List<PricesVO> pricesQueryResult = getPricesQuery().invoke(priceDTO);

        return getPrices(pricesQueryResult);
    }

    private Products getPrices(List<PricesVO> pricesResult) {
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
