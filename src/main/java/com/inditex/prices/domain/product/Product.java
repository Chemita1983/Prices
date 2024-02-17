package com.inditex.prices.domain.product;

import com.inditex.prices.domain.product.brand.Brand;
import lombok.Getter;

import java.util.Optional;

@Getter
public class Product {

    private final ProductId productId;

    private final Brand brand;

    private final StartDate startDate;

    private final EndDate endDate;

    private final PriceList priceList;

    private final Amount price;

    public Product(ProductId productId, Brand brand, StartDate startDate, EndDate endDate, PriceList priceList, Amount price) {
        if(productId == null) throw new IllegalArgumentException("productId cannot be null");
        if(brand == null) throw new IllegalArgumentException("brand cannot be null");
        if(startDate == null) throw new IllegalArgumentException("startDate cannot be null");
        if(priceList == null) throw new IllegalArgumentException("priceList cannot be null");
        if(price == null) throw new IllegalArgumentException("amount cannot be null");
        if(startDate.value().after(endDate.value())) throw new IllegalArgumentException("start date must be greater than end date ");
        this.productId = productId;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.price = price;
    }
}
