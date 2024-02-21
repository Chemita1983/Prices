package com.inditex.prices.infraestructure.api;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.domain.ports.ObtainPrice;
import com.inditex.prices.infraestructure.api.mappers.PricesResponseMapper;
import com.inditex.prices.infraestructure.api.model.PriceResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
public class PricesController {
    private static final String PATH_PRICES = "prices";

    private final ObtainPrice obtainPrice;

    private final PricesResponseMapper pricesResponseMapper;

    public PricesController(ObtainPrice obtainPrice, PricesResponseMapper pricesResponseMapper) {
        this.obtainPrice = obtainPrice;
        this.pricesResponseMapper = pricesResponseMapper;
    }


    @GetMapping(value = PATH_PRICES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceResponseDTO>> getPriceByFilter(@NotNull @RequestParam("productId") Integer productId,
                                                                   @NotNull @RequestParam("brandId") Integer brandId,
                                                                   @Parameter(description = "Start date format: yyyy-MM-dd HH:mm:ss")
                                                                   @NotBlank @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate) {


        ProductQuery productQuery = new ProductQuery(productId, brandId, startDate);

        List<Product> productsWithPrices = obtainPrice.getPriceByFilter(productQuery);

        return new ResponseEntity<>(pricesResponseMapper.mapProductsToPricesResponseDTO(productsWithPrices), HttpStatus.OK);
    }
}

