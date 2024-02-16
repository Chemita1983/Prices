package com.inditex.prices.controller;

import com.inditex.prices.application.obtainPrice.ObtainPrice;
import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.application.obtainPrice.outbound.PriceResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PricesController {

    private static final String PATH_PRICES = "prices";

    private final ObtainPrice obtainPrice;

    public PricesController(ObtainPrice obtainPrice) {
        this.obtainPrice = obtainPrice;
    }


    @GetMapping(value = PATH_PRICES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceResponseDTO>> getPriceByFilter(@RequestParam("productId") Integer productId,
                                                                   @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                                                   @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                                                                   @RequestParam("brandId") Integer brandId) throws ParseException {

        PriceDTO searchFilter = new PriceDTO(productId, brandId, startDate,endDate);

        return new ResponseEntity<>(obtainPrice.getPriceByFilter(searchFilter), HttpStatus.OK);
    }
}

