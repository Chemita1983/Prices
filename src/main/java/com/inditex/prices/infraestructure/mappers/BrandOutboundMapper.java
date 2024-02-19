package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.infraestructure.outbound.model.BrandResponseDTO;
import com.inditex.prices.domain.model.brand.Brand;
import com.inditex.prices.domain.model.brand.BrandId;
import com.inditex.prices.domain.model.brand.Name;
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
