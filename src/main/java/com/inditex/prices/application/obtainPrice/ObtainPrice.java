package com.inditex.prices.application.obtainPrice;

import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.application.obtainPrice.mappers.PricesOutboundMapper;
import com.inditex.prices.application.obtainPrice.outbound.PriceResponseDTO;
import com.inditex.prices.domain.PricesPort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
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
