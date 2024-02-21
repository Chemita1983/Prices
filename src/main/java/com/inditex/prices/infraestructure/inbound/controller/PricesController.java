package com.inditex.prices.infraestructure.inbound.controller;

import com.inditex.prices.infraestructure.inbound.model.PriceDTO;
import com.inditex.prices.infraestructure.outbound.model.PriceResponseDTO;
import com.inditex.prices.application.ObtainPrice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class PricesController {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String PATH_PRICES = "prices";

    private final ObtainPrice obtainPrice;

    public PricesController(ObtainPrice obtainPrice) {
        this.obtainPrice = obtainPrice;
    }


    @GetMapping(value = PATH_PRICES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceResponseDTO>> getPriceByFilter(@RequestParam("productId") Integer productId,
                                                                   @RequestParam(value = "startDate")  String startDate,
                                                                   @RequestParam("brandId") Integer brandId) throws ParseException {



        PriceDTO searchFilter = new PriceDTO(productId, brandId, DATE_FORMAT.parse(startDate));

        return new ResponseEntity<>(obtainPrice.getPriceByFilter(searchFilter), HttpStatus.OK);
    }
}

