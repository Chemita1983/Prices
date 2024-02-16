package com.inditex.prices.infraestructure.entity;

import javax.persistence.*;
import java.util.Date;


@Entity

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

    public PricesVO(){
    }

    public PricesVO(Integer productId, Integer brandId, BrandVO brand, Date startDate, Date endDate, Integer priceList, Integer priority, Double price, String curr) {
        this.productId = productId;
        this.brandId = brandId;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public BrandVO getBrand() {
        return brand;
    }

    public void setBrand(BrandVO brand) {
        this.brand = brand;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}
