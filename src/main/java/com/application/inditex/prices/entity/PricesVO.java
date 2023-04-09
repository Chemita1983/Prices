package com.application.inditex.prices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Prices
 *
 * @author chema
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRICES")
@IdClass(PricesPkVO.class)
public class PricesVO {

    @Id
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Id
    @Column(name = "BRAND_ID")
    private Integer brandId;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", insertable = false, updatable = false)
    private BrandVO brand;

    @Id
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "PRICE_LIST")
    private Integer priceList;

    private Integer priority;

    private Double price;

    private String curr;

}
