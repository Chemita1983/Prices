package com.inditex.prices.infraestructure.mappers;

import com.inditex.prices.domain.product.Product;
import com.inditex.prices.infraestructure.entity.PricesVO;
import org.springframework.stereotype.Component;

@Component
public class PriceServiceMapper {

    public class PriceService {

        private final ProductMapper productMapper;

        public PriceService(ProductMapper productMapper) {
            this.productMapper = productMapper;
        }

        public Product mapToPrice(PricesVO priceVO) {
            return productMapper.mapToPrice(priceVO);
        }
    }
}
