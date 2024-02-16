package com.inditex.prices.domain.price;

import java.util.Date;

public class EndDate {

    private final Date value;

    public EndDate(Date value) {
        if (value == null) throw new IllegalArgumentException("end date value cannot be null");

        this.value = value;
    }

    public Date value() {
        return value;
    }
}
