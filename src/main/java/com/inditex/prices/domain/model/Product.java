package com.inditex.prices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Product {

    private final Integer id;

    private final Brand brand;

    private final Date startDate;

    private final Date endDate;

    private final Integer priceList;

    private final Double price;
}
