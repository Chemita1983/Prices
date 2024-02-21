package com.inditex.prices.domain.model;

import lombok.Getter;

@Getter
public class Brand {

    private final Integer brandId;

    private final String name;

    public Brand(Integer brandId, String name) {
        if(brandId == null) throw new IllegalArgumentException(" brandId cannot be null");
        if(name == null) throw new IllegalArgumentException(" name cannot be null");
        this.brandId = brandId;
        this.name = name;
    }

}
