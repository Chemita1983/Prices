package com.inditex.prices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ProductQuery {

    private Integer productId;

    private Integer brandId;

    private Date startDate;
}
