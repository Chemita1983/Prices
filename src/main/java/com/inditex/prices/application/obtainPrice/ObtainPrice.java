package com.inditex.prices.application.obtainPrice;

import com.inditex.prices.application.obtainPrice.mappers.PricesMapper;
import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.application.obtainPrice.outbound.PriceResponseDTO;
import com.inditex.prices.domain.PricesPort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ObtainPrice {

    private final PricesPort pricesPort;

    private final PricesMapper pricesMapperOutput;

    public ObtainPrice(PricesPort pricesPort, PricesMapper pricesMapperOutput) {
        this.pricesPort = pricesPort;
        this.pricesMapperOutput = pricesMapperOutput;
    }

   public List<PriceResponseDTO> getPriceByFilter(PriceDTO priceDTO) throws ParseException {
        return pricesMapperOutput.mapToDTO(pricesPort.getPricesByFilter(priceDTO));
    }
}
