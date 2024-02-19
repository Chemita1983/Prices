package com.inditex.prices.api.mappers;

import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.infraestructure.model.PriceDto;
import org.mapstruct.Mapper;

@Mapper
public interface PricesInboundMapper {

    PriceDto mapToPriceDto(PriceDTO priceDTO);

}
