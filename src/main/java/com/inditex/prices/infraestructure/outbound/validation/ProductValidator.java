package com.inditex.prices.infraestructure.outbound.validation;

import com.inditex.prices.infraestructure.inbound.model.PriceDTO;
import com.inditex.prices.domain.exceptions.InvalidDatesException;
import com.inditex.prices.domain.exceptions.NullValueException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validInputPrice(PriceDTO priceSearchParams) throws InvalidDatesException, NullValueException {

        if ((priceSearchParams.getStartDate() != null && priceSearchParams.getEndDate() != null) && priceSearchParams.getStartDate().after(priceSearchParams.getEndDate())) {
            throw new InvalidDatesException("start date must be greater than end date");
        }
    }
}
