package com.application.inditex.prices.controller;

import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.exceptions.NullValueException;
import com.application.inditex.prices.input.PriceDTO;
import com.application.inditex.prices.output.PriceResponseDTO;
import com.application.inditex.prices.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;


/**
 * Controller for retrieve prices
 *
 * @author chema
 */
@RestController
public class PricesController {

    private static final String PATH_PRICES = "prices";

    @Autowired
    private PricesService pricesService;

    @GetMapping(value = PATH_PRICES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceResponseDTO>> getPriceByFilter(@RequestParam("productId") Integer productId,
                                                           @RequestParam(value = "startDate", required = false) String startDate,
                                                           @RequestParam(value = "endDate", required = false)  String endDate,
                                                           @RequestParam("brandId") Integer brandId) throws InvalidDatesException, ParseException, NullValueException {

        PriceDTO searchFilter = new PriceDTO(productId, startDate, endDate, brandId);

        return new ResponseEntity<>(pricesService.getPricesByFilter(searchFilter), HttpStatus.OK);
    }
}

