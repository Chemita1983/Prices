package com.inditex.prices.application.mappers;

import com.inditex.prices.application.outbound.PriceResponseDTO;
import com.inditex.prices.domain.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("priceServiceMapperOutbound")
public class PriceServiceMapper {

    private final PricesOutboundMapper priceMapper;

    public PriceServiceMapper(PricesOutboundMapper priceMapper) {
        this.priceMapper = priceMapper;
    }

    public List<PriceResponseDTO> mapToDTO(List<Product> pricesByFilter) {
        return priceMapper.pricesToDTOs(pricesByFilter);
    }

}
