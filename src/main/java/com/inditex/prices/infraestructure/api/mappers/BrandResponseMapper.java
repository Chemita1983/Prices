package com.inditex.prices.infraestructure.api.mappers;

import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.infraestructure.api.model.BrandResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BrandResponseMapper {
    BrandResponseDTO brandToDTO(Brand brand);
}
