package com.inditex.prices.infraestructure.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@MappedSuperclass
public class PricesPkVO implements Serializable {

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
}
