package com.application.inditex.prices.service;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.PricesVO;
import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.exceptions.NullValueException;
import com.application.inditex.prices.input.PriceDTO;
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
    private PricesValidator pricesValidator;

    @Autowired
    private PricesMapper pricesMapper;

    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public List<PriceResponseDTO> getPricesByFilter(PriceDTO priceDTO) throws ParseException, InvalidDatesException, NullValueException {

        log.info("Input:" + priceDTO);

        Price priceSearchParams = pricesMapper.convertToPrice(priceDTO);

        pricesValidator.validInputPrice(priceSearchParams);

        List<PricesVO> priceResult = pricesRepository.getPriceByFilter(priceSearchParams);

        return pricesMapper.convertToPriceResponseDto(priceResult);
    }
}

