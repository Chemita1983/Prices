package com.inditex.prices.infraestructure.api.mappers;

import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.infraestructure.database.entity.BrandVO;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {

    Brand mapToBrand(BrandVO brandVO);

    default Integer mapToBrandId(Integer brandId) {
        return brandId;
    }

    default String mapToName(String name) {
        return name;
    }
}

