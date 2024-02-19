package com.inditex.prices.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Products{

private final List<Product> content;

    public Products(List<Product> content) {
        if (null == content) throw new IllegalArgumentException("Products content must be provided");
        this.content = Collections.unmodifiableList(content);
    }

}
