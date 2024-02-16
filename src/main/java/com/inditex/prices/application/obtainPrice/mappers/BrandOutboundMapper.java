package com.inditex.prices.application.obtainPrice.mappers;

import com.inditex.prices.application.obtainPrice.outbound.BrandResponseDTO;
import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BrandOutboundMapper {
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "name", target = "name")
    BrandResponseDTO brandToDTO(Brand brand);

    default Integer map(BrandId brandId){
        return brandId.value();
    }

    default String map(Name name){
        return name.value();
    }
}
