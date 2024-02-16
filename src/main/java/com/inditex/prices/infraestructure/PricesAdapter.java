package com.inditex.prices.infraestructure;

import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.domain.PricesPort;
import com.inditex.prices.domain.price.Price;
import com.inditex.prices.infraestructure.entity.PricesVO;
import com.inditex.prices.infraestructure.mappers.PricesMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class PricesAdapter implements PricesPort {

    private final PricesMapper pricesMapper;

    private final PricesValidator pricesValidator;

    private final PricesRepository pricesRepository;

    public PricesAdapter(PricesMapper pricesMapper, PricesValidator pricesValidator, PricesRepository pricesRepository) {
        this.pricesMapper = pricesMapper;
        this.pricesValidator = pricesValidator;
        this.pricesRepository = pricesRepository;
    }

    @Override
    public List<Price> getPricesByFilter(PriceDTO priceDTO) {

        pricesValidator.validInputPrice(priceDTO);

        List<PricesVO> priceResult = getQueryResult(priceDTO).orElse(Collections.emptyList());

        return pricesMapper.convertToPriceResponseDto(priceResult);
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
