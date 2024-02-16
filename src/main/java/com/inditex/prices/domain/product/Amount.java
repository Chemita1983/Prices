package com.inditex.prices.domain.product;

public class Amount {

    private final Double value;

    public Amount(Double value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if (value <= 0) throw new IllegalArgumentException("value must be greater than 0");

        this.value = value;
    }

    public Double value() {
        return value;
    }
}
