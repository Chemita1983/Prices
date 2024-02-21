package com.inditex.prices.infraestructure.configuration;

import com.inditex.prices.application.usecase.ObtainPriceUseCase;
import com.inditex.prices.domain.ports.PricesPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ObtainPriceUseCase obtainPrice(PricesPort pricesPort) {
        return new ObtainPriceUseCase(pricesPort);
    }
}
