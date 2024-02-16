package com.application.inditex.prices.service;

import com.inditex.prices.domain.brand.Brand;
import com.inditex.prices.domain.brand.BrandId;
import com.inditex.prices.domain.brand.Name;
import com.inditex.prices.domain.price.*;
import com.inditex.prices.infraestructure.PricesAdapter;
import com.inditex.prices.infraestructure.entity.BrandVO;
import com.inditex.prices.infraestructure.entity.PricesVO;
import com.inditex.prices.domain.exceptions.InvalidDatesException;
import com.inditex.prices.domain.exceptions.NullValueException;
import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.infraestructure.mappers.PricesMapper;
import com.inditex.prices.infraestructure.PricesRepository;
import com.inditex.prices.infraestructure.PricesValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PricesServiceTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final PriceDTO PRICE_DTO ,INVALID_PRICE_DTO;


    static {
        try {
            PRICE_DTO = new PriceDTO(35555, 1, DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"));
            INVALID_PRICE_DTO = new PriceDTO(null, 1,DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @InjectMocks
    PricesAdapter pricesAdapter;

    @Mock
    PricesValidator pricesValidatorMock;

    @Mock
    PricesRepository pricesRepositoryMock;

    @Mock
    PricesMapper pricesMapperMock;

    @Test
    public void givenCorrectParams_getPricesByService_getPriceResponseDTO() throws ParseException {

        when(pricesMapperMock.convertToPriceResponseDto(any(List.class))).thenReturn(getPrices());

        when(pricesRepositoryMock.findByPriceDTOWithDates(any(PriceDTO.class))).thenReturn(getPricesVO());

        List<Price> pricesByFilter = pricesAdapter.getPricesByFilter(PRICE_DTO);

        assertEquals(1, pricesByFilter.size());

        assertThat(pricesByFilter.get(0).productId().value()).isEqualTo(35555);
        assertThat(pricesByFilter.get(0).brandId().value()).isEqualTo(1);
        assertThat(pricesByFilter.get(0).brand().name().value()).isEqualTo("test");
        assertThat(pricesByFilter.get(0).startDate().value()).isEqualTo("2020-06-14 00:00:00");
        assertThat(pricesByFilter.get(0).endDate().map(EndDate::value).orElse(null)).isEqualTo("2020-06-15 00:00:00");
        assertThat(pricesByFilter.get(0).priceList().value()).isEqualTo(1);
        assertThat(pricesByFilter.get(0).amount().value()).isEqualTo(50.0);

    }

    @Test
    public void givenNullParamProductId_getPricesByService_throwNullValuesException() throws InvalidDatesException, ParseException, NullValueException {

        doThrow(new NullValueException("productId cannot be null")).when(pricesValidatorMock).validInputPrice(any(PriceDTO.class));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO);
        });

        assertEquals("productId cannot be null", nullValueException.getMessage());

    }

    @Test
    public void givenNullParamBrandId_getPricesByService_throwNullValuesException() throws InvalidDatesException, ParseException, NullValueException {

        doThrow(new NullValueException("brandId cannot be null")).when(pricesValidatorMock).validInputPrice(any(PriceDTO.class));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO);
        });

        assertEquals("brandId cannot be null", nullValueException.getMessage());

    }

    @Test
    public void givenBadDatesParams_getPricesByService_throwInvalidDatesException() throws InvalidDatesException, ParseException, NullValueException {

        doThrow(new InvalidDatesException("start date must be greater than end date")).when(pricesValidatorMock).validInputPrice(any(PriceDTO.class));

        InvalidDatesException invalidDatesException = Assertions.assertThrows(InvalidDatesException.class, () -> {
            pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO);
        });

        assertEquals("start date must be greater than end date", invalidDatesException.getMessage());

    }

    private List<PricesVO> getPricesVO() throws ParseException {

        return List.of(new PricesVO(35555, 1,  new BrandVO(1, "ZARA"),
                DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 1,50.0, "EUR"));
    }


    private Price getPrice() throws ParseException {

        return new Price(
                    new ProductId(35555),
                    new Brand(new BrandId(1), new Name("test")),
                    new StartDate(DATE_FORMAT.parse("2020-06-14 00:00:00")),
                    new EndDate(DATE_FORMAT.parse("2020-06-15 00:00:00")),
                    new PriceList(1),
                    new Amount(50.0)
        );

    }



    private Price getPriceWithInvalidDates() throws ParseException {

        return new Price(
                new ProductId(35555),
                new Brand(new BrandId(1), new Name("test")),
                new StartDate(DATE_FORMAT.parse("2020-06-18 00:00:00")),
                new EndDate(DATE_FORMAT.parse("2020-06-15 00:00:00")),
                new PriceList(1),
                new Amount(20.0)
        );

    }

    private List<Price> getPrices() throws ParseException {
        return List.of(getPrice());
    }
}
