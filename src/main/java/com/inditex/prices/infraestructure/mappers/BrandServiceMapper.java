package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.infraestructure.entity.BrandVO;
import org.springframework.stereotype.Component;

@Component
public class BrandServiceMapper {

    private final BrandMapper brandMapper;

    public BrandServiceMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public Brand mapToBrand(BrandVO brandVO) {
        return brandMapper.mapToBrand(brandVO);
    }
}
