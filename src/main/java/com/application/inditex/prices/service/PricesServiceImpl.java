package com.application.inditex.prices.service;

import com.application.inditex.prices.domain.PricesEntity;
import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.input.Price;
import com.application.inditex.prices.input.PricesDTO;
import com.application.inditex.prices.mapper.PricesMapper;
import com.application.inditex.prices.output.PriceResponseDTO;
import com.application.inditex.prices.persistence.PricesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@Slf4j
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesMapper pricesMapper;

    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public List<PriceResponseDTO> getPrice(PricesDTO priceDTO) throws ParseException, InvalidDatesException {

        Price priceSearchParams = pricesMapper.convertToPrice(priceDTO);

        validSearchParams(priceSearchParams);

        List<PricesEntity> priceResult = pricesRepository.getPrice(priceSearchParams.getProductId(),
                priceSearchParams.getBrandId(), priceSearchParams.getStartDate(), priceSearchParams.getEndDate());

        return pricesMapper.convertToPriceResponseDto(priceResult);
    }


    private void validSearchParams(Price priceSearchParams) throws InvalidDatesException {
        // Es lo más rápido que se me ha ocurrido, había pensado implementar una validación por anotación o un chain, pero lo hice así por ahorrar tiempo.
        if (priceSearchParams.getStartDate().after(priceSearchParams.getEndDate())) {
            log.error("ProductId: " + priceSearchParams.getProductId() + " start date : + " + priceSearchParams.getStartDate() + "must be greater than End date: " + priceSearchParams.getEndDate());
            throw new InvalidDatesException("start date must be greater than end date");
        }
    }
}
