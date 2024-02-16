package com.inditex.prices.domain.product;

import java.util.Date;

public class StartDate {

    private final Date value;

    public StartDate(Date value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");

        this.value = value;
    }

    public Date value() {
        return value;
    }
}
