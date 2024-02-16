package com.inditex.prices.application.obtainPrice.mappers;

import com.inditex.prices.application.obtainPrice.outbound.BrandResponseDTO;
import com.inditex.prices.application.obtainPrice.outbound.PriceResponseDTO;
import com.inditex.prices.domain.price.EndDate;
import com.inditex.prices.domain.price.Price;
import com.inditex.prices.domain.brand.Brand;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component("pricesMapperOutput")
public class PricesMapper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public List<PriceResponseDTO> mapToDTO(List<Price> pricesByFilter){

        return pricesByFilter.stream()
                .map(this::mapPriceReponseDTO)
                .collect(Collectors.toList());
    }

    private PriceResponseDTO mapPriceReponseDTO(Price price) {

        return new PriceResponseDTO(
                price.productId().value(),
                mapBrand(price.brand()),
                DATE_FORMAT.format(price.startDate().value()),
                DATE_FORMAT.format(price.endDate().map(EndDate::value).orElseGet(() ->null)),
                price.priceList().value(),
                price.amount().value()
        );

    }

    private BrandResponseDTO mapBrand(Brand brand) {

        return new BrandResponseDTO(
                brand.brandId().value(),
                brand.name().value()
        );
    }
}
