package com.application.inditex.prices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
public class PricesVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", insertable = false, updatable = false)
    private BrandVO brand;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private Integer priority;

    private Double price;

    private String curr;

}
