package com.application.inditex.prices.controller;

import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.domain.model.Product;
import com.inditex.prices.domain.model.ProductQuery;
import com.inditex.prices.domain.ports.ObtainPrice;
import com.inditex.prices.infraestructure.api.PricesController;
import com.inditex.prices.infraestructure.api.mappers.PricesResponseMapper;
import com.inditex.prices.infraestructure.api.model.BrandResponseDTO;
import com.inditex.prices.infraestructure.api.model.PriceResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @InjectMocks
    PricesController pricesController;

    @Mock
    ObtainPrice obtainPriceUseCaseMock;

    @Mock
    PricesResponseMapper pricesResponseMapper;

    @Test
    public void givenSearchParams_getPriceByFilter_returnsPrices() throws ParseException {

        when(obtainPriceUseCaseMock.getPriceByFilter(any(ProductQuery.class))).thenReturn(getProductForTest());

        when(pricesResponseMapper.mapProductToPriceResponseDTO(any(Product.class))).thenReturn(getReponseForTest());

        PriceResponseDTO priceByFilter = pricesController.getPriceByFilter(35555, 1, DATE_FORMAT.parse("2020-06-14 00:00:00")).getBody();

        assertNotNull(priceByFilter);
        assertThat(priceByFilter.getProductId()).isEqualTo(35555);
        assertThat(priceByFilter.getStartDate()).isEqualTo("2020-06-14 00:00:00");
        assertThat(priceByFilter.getEndDate()).isEqualTo("2020-06-15 00:00:00");
        assertThat(priceByFilter.getBrand().getId()).isEqualTo(1);
        assertThat(priceByFilter.getBrand().getName()).isEqualTo("ZARA");
        assertThat(priceByFilter.getPrice()).isEqualTo(30.50);
        assertThat(priceByFilter.getPriceList()).isEqualTo(1);
        assertThat(priceByFilter.getPrice()).isEqualTo(30.50);
    }

    private Product getProductForTest() throws ParseException {
        return new Product(35555, new Brand(1, "ZARA"), DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 30.50);
    }

    private PriceResponseDTO getReponseForTest() throws ParseException {
        return new PriceResponseDTO(35555, new BrandResponseDTO(1, "ZARA"), "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1, 30.50);
    }
}
