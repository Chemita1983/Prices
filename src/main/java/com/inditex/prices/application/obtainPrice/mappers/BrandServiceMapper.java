package com.inditex.prices.application.obtainPrice.mappers;

import com.inditex.prices.application.obtainPrice.outbound.BrandResponseDTO;
import com.inditex.prices.domain.product.brand.Brand;
import org.springframework.stereotype.Service;

@Service("brandServiceOutboundMapper")
public class BrandServiceMapper {

    private final BrandOutboundMapper brandMapper;

    public BrandServiceMapper(BrandOutboundMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public BrandResponseDTO mapToDto(Brand brand){
        return brandMapper.brandToDTO(brand);
    }
}
