package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.brand.Brand;
import com.inditex.prices.domain.brand.BrandId;
import com.inditex.prices.domain.brand.Name;
import com.inditex.prices.infraestructure.entity.BrandVO;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

   public Brand mapToBrand(BrandVO brandVO){
       return new Brand(
               new BrandId(brandVO.getBrandId()),
               new Name(brandVO.getName()));
    }
}

