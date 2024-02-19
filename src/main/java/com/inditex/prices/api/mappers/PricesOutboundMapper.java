package com.inditex.prices.api.mappers;

import com.inditex.prices.api.outbound.PriceResponseDTO;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import com.inditex.prices.domain.product.*;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;
import java.util.List;

@Mapper
public interface PricesOutboundMapper {
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
