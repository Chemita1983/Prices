package com.application.inditex.prices.service;

import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.exceptions.NullValueException;
import com.application.inditex.prices.input.PriceDTO;
import com.application.inditex.prices.output.PriceResponseDTO;

import java.text.ParseException;
import java.util.List;

public interface PricesService {

    List<PriceResponseDTO> getPricesByFilter(PriceDTO price) throws ParseException, InvalidDatesException, NullValueException;
}

