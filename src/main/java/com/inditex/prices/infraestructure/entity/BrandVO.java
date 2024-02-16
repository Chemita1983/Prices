package com.inditex.prices.infraestructure.entity;


import javax.persistence.*;

@Entity
@Table(name = "BRAND")
public class BrandVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer brandId;

    private String name;

    public BrandVO(){
    }

    public BrandVO(Integer brandId, String name) {
        this.brandId = brandId;
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public String getName() {
        return name;
    }
}

