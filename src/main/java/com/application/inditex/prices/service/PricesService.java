package com.application.inditex.prices.service;

import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.input.PricesDTO;
import com.application.inditex.prices.output.PriceResponseDTO;

import java.text.ParseException;
import java.util.List;

public interface PricesService {

    List<PriceResponseDTO> getPricesByFilter(PricesDTO price) throws ParseException, InvalidDatesException;
}

