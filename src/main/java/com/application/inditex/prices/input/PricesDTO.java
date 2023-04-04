package com.application.inditex.prices.input;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Input class for search prices {@link com.application.inditex.prices.domain.PricesEntity}
 *
 * @author chema
 */
@AllArgsConstructor
@Getter
public class PricesDTO {

    private Integer  productId;

    private String  startDate;

    private String endDate;

    private Integer brandId;
}
