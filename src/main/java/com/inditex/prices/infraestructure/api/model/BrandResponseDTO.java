package com.inditex.prices.infraestructure.api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BrandResponseDTO {

    private Integer brandId;

    private String name;
}

