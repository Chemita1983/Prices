package com.inditex.prices.application;

import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.api.mappers.PricesOutboundMapper;
import com.inditex.prices.api.outbound.PriceResponseDTO;
import com.inditex.prices.domain.ports.PricesPort;

import java.text.ParseException;
import java.util.List;

public class ObtainPrice {

    private final PricesPort pricesPort;

    private final PricesOutboundMapper pricesMapperOutput;

    public ObtainPrice(PricesPort pricesPort, PricesOutboundMapper pricesMapperOutput) {
        this.pricesPort = pricesPort;
        this.pricesMapperOutput = pricesMapperOutput;
    }

   public List<PriceResponseDTO> getPriceByFilter(PriceDTO priceDTO) throws ParseException {
        return pricesMapperOutput.pricesToDTOs(pricesPort.getPricesByFilter(priceDTO));
    }
}
