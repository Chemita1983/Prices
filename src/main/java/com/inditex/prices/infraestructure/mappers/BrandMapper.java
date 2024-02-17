package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import com.inditex.prices.infraestructure.entity.BrandVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "name", target = "name")
    Brand mapToBrand(BrandVO brandVO);

    default BrandId mapToBrandId(Integer brandId) {
        return new BrandId(brandId);
    }

    default Name mapToName(String name) {
        return new Name(name);
    }
}
