package com.inditex.prices.infraestructure;

import com.inditex.prices.application.inbound.PriceDTO;
import com.inditex.prices.domain.PricesPort;
import com.inditex.prices.domain.product.Product;
import com.inditex.prices.infraestructure.entity.PricesVO;
import com.inditex.prices.infraestructure.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PricesAdapter implements PricesPort {

    private final ProductMapper productMapper;

    private final ProductValidator productValidator;

    private final PricesRepository pricesRepository;

    public PricesAdapter(ProductMapper productMapper, ProductValidator productValidator, PricesRepository pricesRepository) {
        this.productMapper = productMapper;
        this.productValidator = productValidator;
        this.pricesRepository = pricesRepository;
    }

    @Override
    public List<Product> getPricesByFilter(PriceDTO priceDTO) {

        productValidator.validInputPrice(priceDTO);

        return getQueryResult(priceDTO)
                .map(this::getPrices)
                .orElse(Collections.emptyList());

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

    private Optional<List<PricesVO>> getQueryResult(PriceDTO priceDTO) {

        if(null == priceDTO.getStartDate() && null == priceDTO.getEndDate()) {
            return Optional.of(pricesRepository.findByPriceDTOWithoutDates(priceDTO));
        }

        if(null != priceDTO.getStartDate() && null == priceDTO.getEndDate()) {
           return Optional.of(pricesRepository.findByPriceDTOWithStartDate(priceDTO));
        }

        if(null == priceDTO.getStartDate() && null != priceDTO.getEndDate()) {
            return Optional.of(pricesRepository.findByPriceDTOWithEndDate(priceDTO));
        }

        return Optional.of(pricesRepository.findByPriceDTOWithDates(priceDTO));
    }
}
