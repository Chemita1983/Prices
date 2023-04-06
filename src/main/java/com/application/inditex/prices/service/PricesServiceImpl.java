package com.application.inditex.prices.service;

import com.application.inditex.prices.entity.PricesVO;
import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.input.PricesDTO;
import com.application.inditex.prices.mapper.PricesMapper;
import com.application.inditex.prices.output.PriceResponseDTO;
import com.application.inditex.prices.persistence.PricesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


/**
 * Service for retrieving prices
 *
 * @author chema
 */
@Service
@Slf4j
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesMapper pricesMapper;

    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public List<PriceResponseDTO> getPricesByFilter(PricesDTO priceDTO) throws ParseException, InvalidDatesException {

        log.info("Input:" + priceDTO);

        Price priceSearchParams = pricesMapper.convertToPrice(priceDTO);

        validSearchParams(priceSearchParams);

        List<PricesVO> priceResult = pricesRepository.getPriceByFilter(priceSearchParams);

        return pricesMapper.convertToPriceResponseDto(priceResult);
    }

    private void validSearchParams(Price priceSearchParams) throws InvalidDatesException {
        // Es lo más rápido que se me ha ocurrido, había pensado implementar una validación por anotación o un chain, pero lo hice así por ahorrar tiempo.
        if ((priceSearchParams.getStartDate() != null && priceSearchParams.getEndDate() != null)
                && priceSearchParams.getStartDate().after(priceSearchParams.getEndDate())) {
            throw new InvalidDatesException("start date must be greater than end date");
        }
    }
}

