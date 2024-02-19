package com.inditex.prices.infraestructure.configuration;

import com.inditex.prices.api.mappers.PricesInboundMapper;
import com.inditex.prices.api.mappers.PricesOutboundMapper;
import com.inditex.prices.application.ObtainPrice;
import com.inditex.prices.domain.ports.PricesPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ObtainPrice obtainPrice(PricesPort pricesPort, PricesOutboundMapper pricesMapperOutput, PricesInboundMapper pricesInboundMapper) {
        return new ObtainPrice(pricesPort, pricesMapperOutput, pricesInboundMapper);
    }
}
