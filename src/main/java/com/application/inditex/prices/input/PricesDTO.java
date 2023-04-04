package com.application.inditex.prices.input;


import com.application.inditex.prices.persistence.PricesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Input class for search prices {@link PricesEntity}
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
