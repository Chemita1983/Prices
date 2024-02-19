package com.inditex.prices.domain.model.brand;

public class BrandId {

    private final Integer value;

    public BrandId(Integer value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if (value <= 0) throw new IllegalArgumentException("value must be greater than 0");

        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
