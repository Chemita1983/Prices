package com.inditex.prices.application;

import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.api.mappers.PricesInboundMapper;
import com.inditex.prices.api.mappers.PricesOutboundMapper;
import com.inditex.prices.api.outbound.PriceResponseDTO;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infraestructure.model.PriceDto;

import java.util.List;

public class ObtainPrice {

    private final PricesPort pricesPort;

    private final PricesOutboundMapper pricesMapperOutput;

    private final PricesInboundMapper pricesInboundMapper;

    public ObtainPrice(PricesPort pricesPort, PricesOutboundMapper pricesMapperOutput, PricesInboundMapper pricesInboundMapper) {
        this.pricesPort = pricesPort;
        this.pricesMapperOutput = pricesMapperOutput;
        this.pricesInboundMapper = pricesInboundMapper;
    }

   public List<PriceResponseDTO> getPriceByFilter(PriceDTO priceDTO) {

       PriceDto priceDto = pricesInboundMapper.mapToPriceDto(priceDTO);
       return pricesMapperOutput.pricesToDTOs(pricesPort.getPricesByFilter(priceDto));
    }
}
