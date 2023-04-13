package com.application.inditex.prices.mapper;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.PricesVO;
import com.application.inditex.prices.input.PriceDTO;
import com.application.inditex.prices.output.PriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    public Price convertToPrice(PriceDTO priceDTO) throws ParseException {

        return Price.builder()
                .productId(priceDTO.getProductId())
                .brandId(priceDTO.getBrandId())
                .startDate(mapDate(priceDTO.getStartDate()))
                .endDate(mapDate(priceDTO.getEndDate()))
                .build();
    }

    public List<PriceResponseDTO> convertToPriceResponseDto(List<PricesVO> priceResult) {
        if (priceResult == null || priceResult.isEmpty()) return new ArrayList<>();

        Integer maxPriority = getPriceResultMaxPriority(priceResult);

        return priceResult.stream()
                .filter(price -> price.getPriority().equals(maxPriority))
                .map(this::mapToPricesResponseDTO)
                .collect(Collectors.toList());
    }

    private Integer getPriceResultMaxPriority(List<PricesVO> priceResult) {

        return Collections.max(priceResult.stream().map(PricesVO::getPriority).collect(Collectors.toList()));
    }

    private Date mapDate(String date) throws ParseException {
        return date != null ? DATE_FORMAT.parse(date) : null;
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
