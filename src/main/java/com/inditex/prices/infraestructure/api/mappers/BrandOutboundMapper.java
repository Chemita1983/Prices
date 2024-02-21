package com.inditex.prices.infraestructure.api.mappers;

import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.infraestructure.api.model.BrandResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BrandOutboundMapper {
    BrandResponseDTO brandToDTO(Brand brand);

    default Integer mapBrandId(Integer brandId){
        return brandId;
    }

    default String mapName(String name){
        return name;
    }
}
