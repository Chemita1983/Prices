package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import com.inditex.prices.domain.product.*;
import com.inditex.prices.infraestructure.entity.BrandVO;
import com.inditex.prices.infraestructure.entity.PricesVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "price", target = "price")
    Product mapToPrice(PricesVO priceVO);

    default ProductId mapToProductId(Integer productId) {
        return new ProductId(productId);
    }

    default Brand mapToBrand(BrandVO brandVO){
        return new Brand(new BrandId(brandVO.getBrandId()), new Name(brandVO.getName()));
    }

    default StartDate mapToStartDate(Date startDate){
        return new StartDate(startDate);
    }

    default EndDate mapToEndDate(Date endDate){
        return new EndDate(endDate);
    }

    default PriceList mapToPriceList(Integer priceList){
        return new PriceList(priceList);
    }

    default Amount mapToAmount(Double amount){
        return new Amount(amount);
    }
}
