package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.price.*;
import com.inditex.prices.infraestructure.entity.PricesVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricesMapper {

    private final BrandMapper brandMapper;

    public PricesMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public Price mapToPrice(PricesVO priceVO) {

        return new Price(
                new ProductId(priceVO.getProductId()),
                brandMapper.mapToBrand(priceVO.getBrand()),
                new StartDate(priceVO.getStartDate()),
                new EndDate(priceVO.getEndDate()),
                new PriceList(priceVO.getPriceList()),
                new Amount(priceVO.getPrice())
        );
    }
}
