package com.application.inditex.prices.service;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.exceptions.NullValueException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PricesValidator {

    public void validInputPrice(Price priceSearchParams) throws InvalidDatesException, NullValueException {
        if (Objects.isNull(priceSearchParams.getProductId()))
            throw new NullValueException("productId cannot be null");

        if (Objects.isNull(priceSearchParams.getBrandId()))
            throw new NullValueException("brandId cannot be null");

        if ((priceSearchParams.getStartDate() != null && priceSearchParams.getEndDate() != null)
                && priceSearchParams.getStartDate().after(priceSearchParams.getEndDate())) {
            throw new InvalidDatesException("start date must be greater than end date");
        }
    }
}
