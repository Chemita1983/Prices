package com.inditex.prices.application;

import com.inditex.prices.infraestructure.inbound.model.PriceDTO;
import com.inditex.prices.infraestructure.mappers.PricesOutboundMapper;
import com.inditex.prices.infraestructure.outbound.model.PriceResponseDTO;
import com.inditex.prices.domain.ports.PricesPort;

import java.util.List;

public class ObtainPrice {

    private final PricesPort pricesPort;

    private final PricesOutboundMapper pricesMapperOutput;

    public ObtainPrice(PricesPort pricesPort, PricesOutboundMapper pricesMapperOutput) {
        this.pricesPort = pricesPort;
        this.pricesMapperOutput = pricesMapperOutput;
    }

   public List<PriceResponseDTO> getPriceByFilter(PriceDTO priceDTO) {
       return pricesMapperOutput.pricesToDTOs(pricesPort.getPricesByFilter(priceDTO));
    }
}
