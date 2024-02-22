package com.inditex.prices.infraestructure.api.mappers;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.infraestructure.api.model.BrandResponseDTO;
import com.inditex.prices.infraestructure.api.model.PriceResponseDTO;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;

@Mapper
public interface PricesResponseMapper {
    default PriceResponseDTO mapProductToPriceResponseDTO(Product product) {
        if(product == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new PriceResponseDTO(product.getId(),
                new BrandResponseDTO(product.getBrand().getId(), product.getBrand().getName()),
                dateFormat.format(product.getStartDate()),
                dateFormat.format(product.getEndDate()),
                product.getPriceList(),
                product.getPrice());
    }

}
