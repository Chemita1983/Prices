package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.product.*;
import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import com.inditex.prices.infraestructure.repository.entity.BrandVO;
import com.inditex.prices.infraestructure.repository.entity.PricesVO;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    default Products mapToProducts(List<PricesVO> pricesVOList) {
        List<Product> productList = pricesVOList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
        return new Products(productList);
    }

   default Product mapToProduct(PricesVO pricesVO){
        return  new Product(new ProductId(pricesVO.getProductId()),
                            new Brand(new BrandId(pricesVO.getBrandId()),
                                      new Name(pricesVO.getBrand().getName())),
                            new StartDate(pricesVO.getStartDate()),
                            new EndDate(pricesVO.getEndDate()),
                            new PriceList(pricesVO.getPriceList()),
                            new Amount(pricesVO.getPrice()));
   }

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
