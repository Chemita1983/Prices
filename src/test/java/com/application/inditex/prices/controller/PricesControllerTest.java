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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        when(obtainPriceUseCaseMock.getPriceByFilter(any(ProductQuery.class))).thenReturn(getProductsForTest());

        when(pricesResponseMapper.mapProductsToPricesResponseDTO(any(List.class))).thenReturn(getReponseForTest());

        List<PriceResponseDTO> priceByFilter = pricesController.getPriceByFilter(35555, 1, DATE_FORMAT.parse("2020-06-14 00:00:00")).getBody();

        assertNotNull(priceByFilter);
        assertEquals(1, priceByFilter.size());
        assertThat(priceByFilter.get(0).getProductId()).isEqualTo(35555);
        assertThat(priceByFilter.get(0).getStartDate()).isEqualTo("2020-06-14 00:00:00");
        assertThat(priceByFilter.get(0).getEndDate()).isEqualTo("2020-06-15 00:00:00");
        assertThat(priceByFilter.get(0).getBrand().getId()).isEqualTo(1);
        assertThat(priceByFilter.get(0).getBrand().getName()).isEqualTo("ZARA");
        assertThat(priceByFilter.get(0).getPrice()).isEqualTo(30.50);
        assertThat(priceByFilter.get(0).getPriceList()).isEqualTo(1);
        assertThat(priceByFilter.get(0).getPrice()).isEqualTo(30.50);
    }

    private List<Product> getProductsForTest() throws ParseException {
        return Collections.singletonList(new Product(35555, new Brand(1, "ZARA"), DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 30.50));
    }

    private List<PriceResponseDTO> getReponseForTest() throws ParseException {
        return Collections.singletonList(new PriceResponseDTO(35555, new BrandResponseDTO(1, "ZARA"), "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1, 30.50));
    }
}
