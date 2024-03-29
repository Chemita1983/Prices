package com.inditex.prices.infraestructure.database.mappers;

import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.domain.model.Product;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    default Product mapToProduct(PricesVO pricesVO) {
        if(pricesVO == null ) return null;
        return new Product(pricesVO.getProductId(),
                new Brand(pricesVO.getBrandId(), pricesVO.getBrand().getName()),
                pricesVO.getStartDate(),
                pricesVO.getEndDate(),
                pricesVO.getPriceList(),
                pricesVO.getPrice());
    }

}
