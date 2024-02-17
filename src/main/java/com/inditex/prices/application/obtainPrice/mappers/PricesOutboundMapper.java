package com.inditex.prices.application.obtainPrice.mappers;

import com.inditex.prices.application.obtainPrice.outbound.PriceResponseDTO;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import com.inditex.prices.domain.product.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PricesOutboundMapper {


    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate", defaultValue = "null")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "price", target = "price")
    List<PriceResponseDTO> pricesToDTOs(List<Product> price);

    default Integer map(ProductId productId){
        return productId.value();
    }

    default Integer map(BrandId brandId){
        return brandId.value();
    }

    default String map(Name name){
        return name.value();
    }

    default String map(StartDate startDate){
        if(startDate == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(startDate.value());
    }

    default String map(EndDate endDate){
        if(endDate == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(endDate.value());
    }

    default Integer map(PriceList priceList){
        return priceList.value();
    }

    default Double map(Amount amount){
        return amount.value();
    }
}
