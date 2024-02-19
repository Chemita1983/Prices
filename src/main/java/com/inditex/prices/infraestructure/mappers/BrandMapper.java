package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.model.brand.Brand;
import com.inditex.prices.domain.model.brand.BrandId;
import com.inditex.prices.domain.model.brand.Name;
import com.inditex.prices.infraestructure.outbound.repository.entity.BrandVO;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {

    Brand mapToBrand(BrandVO brandVO);

    default BrandId mapToBrandId(Integer brandId) {
        return new BrandId(brandId);
    }

    default Name mapToName(String name) {
        return new Name(name);
    }
}

