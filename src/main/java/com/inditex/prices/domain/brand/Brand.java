package com.inditex.prices.domain.brand;

public class Brand {

    private final BrandId brandId;

    private final Name name;

    public Brand(BrandId brandId, Name name) {
        if(brandId == null) throw new IllegalArgumentException(" brandId cannot be null");
        if(name == null) throw new IllegalArgumentException(" name cannot be null");
        this.brandId = brandId;
        this.name = name;
    }

    public BrandId brandId() {
        return brandId;
    }

    public Name name() {
        return name;
    }
}
