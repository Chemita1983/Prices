package com.application.inditex.prices.mapper;

import com.application.inditex.prices.domain.PricesEntity;
import com.application.inditex.prices.input.Price;
import com.application.inditex.prices.input.PricesDTO;
import com.application.inditex.prices.output.PriceResponseDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricesMapper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Price convertToPrice(PricesDTO pricesDTO) throws ParseException {

        return Price.builder()
                .productId(pricesDTO.getProductId())
                .brandId(pricesDTO.getBrandId())
                .startDate(DATE_FORMAT.parse(pricesDTO.getStartDate()))
                .endDate(DATE_FORMAT.parse(pricesDTO.getEndDate()))
                .build();
    }

    public List<PriceResponseDTO> convertToPriceResponseDto(List<PricesEntity> priceResult) {

        return priceResult.stream()
                .map(this::mapToPricesResponseDTO)
                .collect(Collectors.toList());
    }

    private PriceResponseDTO mapToPricesResponseDTO(PricesEntity priceEntity) {

        return new PriceResponseDTO(priceEntity.getProductId(),
                priceEntity.getBrandId(),
                DATE_FORMAT.format(priceEntity.getStartDate()),
                DATE_FORMAT.format(priceEntity.getEndDate()),
                priceEntity.getPriceList(),
                priceEntity.getPrice());

    }
}
