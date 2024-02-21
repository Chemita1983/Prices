package com.inditex.prices.infraestructure.api.mappers;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.infraestructure.api.model.BrandResponseDTO;
import com.inditex.prices.infraestructure.api.model.PriceResponseDTO;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PricesResponseMapper {

    default List<PriceResponseDTO> mapProductsToPricesResponseDTO(List<Product> products){
        return products.stream()
                .map(this::mapProductToPriceResponseDTO)
                .collect(Collectors.toList());
    }

    default PriceResponseDTO mapProductToPriceResponseDTO(Product product){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new PriceResponseDTO(product.getId(),
                new BrandResponseDTO(product.getBrand().getId(), product.getBrand().getName()),
                dateFormat.format(product.getStartDate()),
                dateFormat.format(product.getEndDate()),
                product.getPriceList(),
                product.getPrice());
    }

    default Integer mapProductId(Integer productId){
        return productId;
    }

    default Integer mapBrandId(Integer brandId){
        return brandId;
    }

    default String mapName(String name){
        return name;
    }

    default String mapStartDate(Date startDate){
        if(startDate == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(startDate);
    }

    default String mapEndDate(Date endDate){
        if(endDate == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(endDate);
    }

    default Integer mapPriceList(Integer priceList){
        return priceList;
    }

    default Double mapAmount(Double amount){
        return amount;
    }
}
