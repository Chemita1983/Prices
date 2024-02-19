package com.inditex.prices.infraestructure.adapters.validation;

import com.inditex.prices.domain.exceptions.InvalidDatesException;
import com.inditex.prices.domain.exceptions.NullValueException;
import com.inditex.prices.infraestructure.model.PriceDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validInputPrice(PriceDto priceSearchParams) throws InvalidDatesException, NullValueException {

        if ((priceSearchParams.getStartDate() != null && priceSearchParams.getEndDate() != null) && priceSearchParams.getStartDate().after(priceSearchParams.getEndDate())) {
            throw new InvalidDatesException("start date must be greater than end date");
        }
    }
}
