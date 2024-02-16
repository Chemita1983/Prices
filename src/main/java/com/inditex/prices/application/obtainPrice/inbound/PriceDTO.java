package com.inditex.prices.application.obtainPrice.inbound;

import java.util.Date;


public class PriceDTO {

    private final Integer productId;

    private final Integer brandId;

    private final Date startDate;

    private final Date endDate;

    public PriceDTO(Integer productId, Integer brandId, Date startDate, Date endDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
