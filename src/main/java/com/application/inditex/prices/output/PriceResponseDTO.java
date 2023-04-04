package com.application.inditex.prices.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that wrapped {@link com.application.inditex.prices.domain.PricesEntity} result for response
 *
 * @author chema
 */
@AllArgsConstructor
@Getter
@Setter
public class PriceResponseDTO {

    private Integer productId;

    private Integer brandId;

    private String startDate;

    private String endDate;

    private Integer priceList;

    private Double price;
}
