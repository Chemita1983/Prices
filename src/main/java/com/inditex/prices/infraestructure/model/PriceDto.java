package com.inditex.prices.infraestructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PriceDto {

    private Integer productId;

    private Integer brandId;

    private Date startDate;

    private Date endDate;
}
