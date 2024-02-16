package com.inditex.prices.domain.brand;

public class Name {

    private final String value;

    public Name(String value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");

        this.value = value;
    }

    public String value() {
        return value;
    }
}
