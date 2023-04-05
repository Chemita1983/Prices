package com.application.inditex.prices.mapper;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.input.PricesDTO;
import com.application.inditex.prices.output.PriceResponseDTO;
import com.application.inditex.prices.persistence.PricesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper class to transform Prices entity to prices response dto
 */
@Component
public class PricesMapper {

    @Autowired
    private BrandMapper brandMapper;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Price convertToPrice(PricesDTO pricesDTO) throws ParseException {

        return Price.builder()
                .productId(pricesDTO.getProductId())
                .brandId(pricesDTO.getBrandId())
                .startDate(DATE_FORMAT.parse(pricesDTO.getStartDate()))
                .endDate(DATE_FORMAT.parse(pricesDTO.getEndDate()))
                .build();
    }

    public List<PriceResponseDTO> convertToPriceResponseDto(List<PricesVO> priceResult) {

        return priceResult.stream()
                .map(this::mapToPricesResponseDTO)
                .collect(Collectors.toList());
    }

    private PriceResponseDTO mapToPricesResponseDTO(PricesVO priceVO) {

        return new PriceResponseDTO(priceVO.getProductId(),
                brandMapper.convertToBrandResponseDTO(priceVO.getBrand()),
                DATE_FORMAT.format(priceVO.getStartDate()),
                DATE_FORMAT.format(priceVO.getEndDate()),
                priceVO.getPriceList(),
                priceVO.getPrice());
    }
}
