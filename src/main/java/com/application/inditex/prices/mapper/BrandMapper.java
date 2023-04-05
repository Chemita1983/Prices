package com.application.inditex.prices.mapper;

import com.application.inditex.prices.output.BrandResponseDTO;
import com.application.inditex.prices.entity.BrandVO;
import org.springframework.stereotype.Component;


/**
 * Mapper class to convert BrandEntity to BrandResponseDto
 */
@Component
public class BrandMapper {

   public BrandResponseDTO convertToBrandResponseDTO(BrandVO brandVO){
       return new BrandResponseDTO(brandVO.getBrandId(), brandVO.getName());
   }
}

