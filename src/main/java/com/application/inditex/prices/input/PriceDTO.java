package com.application.inditex.prices.input;


import com.application.inditex.prices.entity.PricesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Input class for search prices {@link PricesVO}
 *
 * @author chema
 */
@AllArgsConstructor
@Getter
public class PriceDTO {

    private Integer  productId;

    private String  startDate;

    private String endDate;

    private Integer brandId;
}
