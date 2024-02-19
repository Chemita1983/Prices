package com.inditex.prices.api.outbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class PriceResponseDTO {

    private Integer productId;

    private BrandResponseDTO brand;

    private String startDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endDate;

    private Integer priceList;

    private Double price;
}

