package com.application.inditex.prices.controller;

import com.inditex.prices.application.ObtainPrice;
import com.inditex.prices.api.PricesController;
import com.inditex.prices.domain.exceptions.InvalidDatesException;
import com.inditex.prices.domain.exceptions.NullValueException;
import com.inditex.prices.api.inbound.PriceDTO;
import com.inditex.prices.api.outbound.BrandResponseDTO;
import com.inditex.prices.api.outbound.PriceResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {

    @InjectMocks
    PricesController pricesController;

    @Mock
    ObtainPrice obtainPriceMock;

    @Test
    public void givenSearchParams_getPriceByFilter_returnsPrices() throws InvalidDatesException, ParseException, NullValueException {

        when(obtainPriceMock.getPriceByFilter(any(PriceDTO.class))).thenReturn(getPriceResponseForTest());

        List<PriceResponseDTO> priceByFilter = pricesController.getPriceByFilter(35555, "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1).getBody();

        assertThat(priceByFilter).isNotNull();
        assertThat(priceByFilter.size()).isEqualTo(1);
        assertThat(priceByFilter.get(0).getProductId()).isEqualTo(35555);
        assertThat(priceByFilter.get(0).getStartDate()).isEqualTo("2020-06-14 00:00:00");
        assertThat(priceByFilter.get(0).getEndDate()).isEqualTo("2020-06-15 00:00:00");
        assertThat(priceByFilter.get(0).getBrand().getBrandId()).isEqualTo(1);
        assertThat(priceByFilter.get(0).getBrand().getName()).isEqualTo("ZARA");
    }


    @Test
    public void givenBadSearchParams_getPriceByFilter_returnException() throws InvalidDatesException, ParseException, NullValueException {

        when(obtainPriceMock.getPriceByFilter(any(PriceDTO.class))).thenThrow(new NullValueException("ProductId cannot be null"));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesController.getPriceByFilter(null,  "2020-06-14 00:00:00",  "2020-06-15 00:00:00", 1);
        });

        Assertions.assertEquals("ProductId cannot be null", nullValueException.getMessage());
    }

    @Test
    public void givenBadDatesParams_getPriceByFilter_returnException() throws InvalidDatesException, ParseException, NullValueException {

        when(obtainPriceMock.getPriceByFilter(any(PriceDTO.class))).thenThrow(new InvalidDatesException("start date must be greater than end date"));

        InvalidDatesException invalidDatesException = Assertions.assertThrows(InvalidDatesException.class, () -> {
            pricesController.getPriceByFilter(35555,  "2020-06-16 00:00:00",  "2020-06-15 00:00:00", 1);
        });

        Assertions.assertEquals("start date must be greater than end date", invalidDatesException.getMessage());
    }


    private List<PriceResponseDTO> getPriceResponseForTest() {
        return Collections.singletonList(new PriceResponseDTO(35555, new BrandResponseDTO(1, "ZARA"), "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1, 30.50));
    }
}
