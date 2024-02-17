package com.inditex.prices.application.inbound;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class PriceDTO {

    private Integer productId;

    private Integer brandId;

    private Date startDate;

    private Date endDate;
}
