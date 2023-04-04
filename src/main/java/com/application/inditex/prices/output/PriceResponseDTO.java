package com.application.inditex.prices.output;

import com.application.inditex.prices.persistence.PricesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that wrapped {@link PricesEntity} result for response
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
