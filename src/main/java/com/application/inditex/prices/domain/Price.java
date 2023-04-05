package com.application.inditex.prices.domain;

import com.application.inditex.prices.persistence.PricesVO;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


/**
 * Domain object for retrieving {@link PricesVO}
 *
 * @autor chema;
 */

@Builder
@Getter
public class Price {

    private Integer productId;

    private Integer brandId;

    private String name;

    private Date startDate;

    private Date endDate;
}
