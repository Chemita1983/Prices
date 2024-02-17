package com.inditex.prices.application.outbound;

import com.fasterxml.jackson.annotation.JsonInclude;


public class PriceResponseDTO {

    private Integer productId;

    private BrandResponseDTO brand;

    private String startDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endDate;

    private Integer priceList;

    private Double price;

    public PriceResponseDTO(Integer productId, BrandResponseDTO brand, String startDate, String endDate, Integer priceList, Double price) {
        this.productId = productId;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BrandResponseDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandResponseDTO brand) {
        this.brand = brand;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

