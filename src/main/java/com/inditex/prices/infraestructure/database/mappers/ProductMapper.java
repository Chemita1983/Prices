package com.inditex.prices.infraestructure.database.mappers;

import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    default List<Product> mapToProducts(List<PricesVO> pricesVOList) {
        return pricesVOList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    default Product mapToProduct(PricesVO pricesVO) {
        return new Product(pricesVO.getProductId(),
                new Brand(pricesVO.getBrandId(), pricesVO.getBrand().getName()),
                pricesVO.getStartDate(),
                pricesVO.getEndDate(),
                pricesVO.getPriceList(),
                pricesVO.getPrice());
    }

}
