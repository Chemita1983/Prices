package com.inditex.prices.infraestructure.database.mappers;

import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.domain.model.Product;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

   List<Product> mapToProducts(List<PricesVO> pricesVOList);

    default Product mapToProduct(PricesVO pricesVO) {
        return new Product(pricesVO.getProductId(),
                new Brand(pricesVO.getBrandId(), pricesVO.getBrand().getName()),
                pricesVO.getStartDate(),
                pricesVO.getEndDate(),
                pricesVO.getPriceList(),
                pricesVO.getPrice());
    }

}
