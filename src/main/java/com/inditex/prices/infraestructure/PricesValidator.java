package com.inditex.prices.infraestructure;

import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.domain.exceptions.InvalidDatesException;
import com.inditex.prices.domain.exceptions.NullValueException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class PricesValidator {

    public void validInputPrice(PriceDTO priceSearchParams) throws InvalidDatesException, NullValueException {

        if (Objects.isNull(priceSearchParams.getProductId()))
            throw new NullValueException("productId cannot be null");

        if (Objects.isNull(priceSearchParams.getBrandId()))
            throw new NullValueException("brandId cannot be null");

        if ((priceSearchParams.getStartDate() != null && priceSearchParams.getEndDate() != null) && priceSearchParams.getStartDate().after(priceSearchParams.getEndDate())) {
            throw new InvalidDatesException("start date must be greater than end date");
        }
    }
}
