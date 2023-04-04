package com.application.inditex.prices.domain;

import com.application.inditex.prices.persistence.PricesEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


/**
 * Domain object for retrieving {@link PricesEntity}
 *
 * @autor chema;
 */

@Builder
@Getter
public class Price {

    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Integer productId;
}
