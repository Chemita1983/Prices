package com.inditex.prices.infraestructure;

import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.domain.product.Product;
import com.inditex.prices.infraestructure.entity.PricesVO;
import com.inditex.prices.infraestructure.mappers.ProductMapper;
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
    public List<Product> getPricesByFilter(PriceDTO priceDTO) {
        productValidator.validInputPrice(priceDTO);

        List<PricesVO> pricesQueryResult = getPricesQuery().invoke(priceDTO);

        return getPrices(pricesQueryResult);
    }

    private List<Product> getPrices(List<PricesVO> pricesResult) {
        return pricesResult.stream()
                .filter(price -> price.getPriority().equals(getPriceResultMaxPriority(pricesResult)))
                .map(productMapper::mapToPrice)
                .collect(Collectors.toList());
    }

    private Integer getPriceResultMaxPriority(List<PricesVO> priceResult) {
        return Collections.max(priceResult.stream().map(PricesVO::getPriority).collect(Collectors.toList()));
    }

    private QueryInvoker getPricesQuery() {
        return pricesRepository::findByPriceDTOWithDates;
    }
}
