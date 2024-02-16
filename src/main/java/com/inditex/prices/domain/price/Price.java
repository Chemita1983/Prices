package com.inditex.prices.domain.price;

import com.inditex.prices.domain.brand.Brand;
import com.inditex.prices.domain.brand.BrandId;

import java.util.Optional;

public class Price {

    private final ProductId productId;

    private final Brand brand;

    private final StartDate startDate;

    private final EndDate endDate;

    private final PriceList priceList;

    private final Amount amount;

    public Price(ProductId productId, Brand brand, StartDate startDate, EndDate endDate, PriceList priceList, Amount amount) {
        if(productId == null) throw new IllegalArgumentException("productId cannot be null");
        if(brand == null) throw new IllegalArgumentException("brand cannot be null");
        if(startDate == null) throw new IllegalArgumentException("startDate cannot be null");
        if(priceList == null) throw new IllegalArgumentException("priceList cannot be null");
        if(amount == null) throw new IllegalArgumentException("amount cannot be null");
        if(startDate.value().after(endDate.value())) throw new IllegalArgumentException("start date must be greater than end date ");
        this.productId = productId;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.amount = amount;
    }

    public ProductId productId() {return productId;}

    public Brand brand() {
        return brand;
    }

    public StartDate startDate() {
        return startDate;
    }

    public Optional<EndDate> endDate() {
        return Optional.of(endDate);
    }

    public BrandId brandId(){
        return brand.brandId();
    }

    public PriceList priceList() {return priceList; }

    public Amount amount() {return amount;}
}
