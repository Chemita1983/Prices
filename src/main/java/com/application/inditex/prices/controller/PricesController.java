package com.application.inditex.prices.controller;

import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.input.PricesDTO;
import com.application.inditex.prices.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


/**
 * Controller for retrieve prices
 *
 * @author chema
 */
@RestController
public class PricesController {

    private static final String PATH_PRICES = "prices";

    private static final String PRODUCT_ID = "/{productId}";

    @Autowired
    private PricesService pricesService;

    @GetMapping(value = PATH_PRICES + PRODUCT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPrice(@PathVariable("productId") Integer productId,
                                   @RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate,
                                   @RequestParam("brandId") Integer brandId) throws InvalidDatesException, ParseException {

        PricesDTO searchFilter = new PricesDTO(productId, startDate, endDate, brandId);

        return new ResponseEntity<>(pricesService.getPrice(searchFilter), HttpStatus.OK);
    }
}
