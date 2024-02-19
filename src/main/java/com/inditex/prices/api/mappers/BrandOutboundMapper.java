package com.inditex.prices.api.mappers;

import com.inditex.prices.api.outbound.BrandResponseDTO;
import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import org.mapstruct.Mapper;

@Mapper
public interface BrandOutboundMapper {
    BrandResponseDTO brandToDTO(Brand brand);

    default Integer map(BrandId brandId){
        return brandId.value();
    }

    default String map(Name name){
        return name.value();
    }
}
