package com.application.inditex.prices.service;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.BrandVO;
import com.application.inditex.prices.entity.PricesVO;
import com.application.inditex.prices.exceptions.InvalidDatesException;
import com.application.inditex.prices.exceptions.NullValueException;
import com.application.inditex.prices.input.PriceDTO;
import com.application.inditex.prices.mapper.PricesMapper;
import com.application.inditex.prices.output.BrandResponseDTO;
import com.application.inditex.prices.output.PriceResponseDTO;
import com.application.inditex.prices.persistence.PricesRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PricesServiceTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final PriceDTO PRICE_DTO ,INVALID_PRICE_DTO, BAD_DATES_PRICE_DTO;

    private static final BrandResponseDTO BRAND_RESPONSE_DTO;

    static {
        PRICE_DTO = new PriceDTO(35555, "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1);

        INVALID_PRICE_DTO = new PriceDTO(null, "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1);

        BAD_DATES_PRICE_DTO = new PriceDTO(35555, "2020-06-18 00:00:00", "2020-06-15 00:00:00", 1);

        BRAND_RESPONSE_DTO = new BrandResponseDTO(1, "ZARA");
    }

    @InjectMocks
    PricesServiceImpl pricesService;

    @Mock
    PricesValidator pricesValidator;

    @Mock
    PricesRepository pricesRepository;

    @Mock
    PricesMapper pricesMapper;

    @Test
    public void givenCorrectParams_getPricesByService_getPriceResponseDTO() throws InvalidDatesException, ParseException, NullValueException {

        when(pricesMapper.convertToPrice(any(PriceDTO.class))).thenReturn(getPrice());

        doNothing().when(pricesValidator).validInputPrice(any(Price.class));

        when(pricesMapper.convertToPriceResponseDto(any(List.class))).thenReturn(getPricesResponseDTO());

        when(pricesRepository.getPriceByFilter(any(Price.class))).thenReturn(getPricesVO());

        List<PriceResponseDTO> pricesByFilter = pricesService.getPricesByFilter(PRICE_DTO);

        assertThat(pricesByFilter.size()).isEqualTo(1);
        assertThat(pricesByFilter.get(0).getProductId()).isEqualTo(35555);
        assertThat(pricesByFilter.get(0).getBrand()).isEqualTo(BRAND_RESPONSE_DTO);
        assertThat(pricesByFilter.get(0).getStartDate()).isEqualTo("2020-06-14 00:00:00");
        assertThat(pricesByFilter.get(0).getEndDate()).isEqualTo("2020-06-15 00:00:00");
        assertThat(pricesByFilter.get(0).getPriceList()).isEqualTo(1);
        assertThat(pricesByFilter.get(0).getPrice()).isEqualTo(50.0);

    }

    @Test
    public void givenNullParamProductId_getPricesByService_throwNullValuesException() throws InvalidDatesException, ParseException, NullValueException {

        when(pricesMapper.convertToPrice(any(PriceDTO.class))).thenReturn(getPriceWithNullProductId());

        doThrow(new NullValueException("productId cannot be null")).when(pricesValidator).validInputPrice(any(Price.class));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesService.getPricesByFilter(INVALID_PRICE_DTO);
        });

        Assertions.assertEquals("productId cannot be null", nullValueException.getMessage());

    }

    @Test
    public void givenNullParamBrandId_getPricesByService_throwNullValuesException() throws InvalidDatesException, ParseException, NullValueException {

        when(pricesMapper.convertToPrice(any(PriceDTO.class))).thenReturn(getPriceWithNullBrandId());

        doThrow(new NullValueException("brandId cannot be null")).when(pricesValidator).validInputPrice(any(Price.class));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesService.getPricesByFilter(INVALID_PRICE_DTO);
        });

        Assertions.assertEquals("brandId cannot be null", nullValueException.getMessage());

    }

    @Test
    public void givenBadDatesParams_getPricesByService_throwInvalidDatesException() throws InvalidDatesException, ParseException, NullValueException {

        when(pricesMapper.convertToPrice(any(PriceDTO.class))).thenReturn(getPriceWithInvalidDates());

        doThrow(new InvalidDatesException("start date must be greater than end date")).when(pricesValidator).validInputPrice(any(Price.class));

        InvalidDatesException invalidDatesException = Assertions.assertThrows(InvalidDatesException.class, () -> {
            pricesService.getPricesByFilter(INVALID_PRICE_DTO);
        });

        Assertions.assertEquals("start date must be greater than end date", invalidDatesException.getMessage());

    }

    private List<PricesVO> getPricesVO() throws ParseException {

        return List.of(new PricesVO(35555, 1,  new BrandVO(1, "ZARA"),
                DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 1,50.0, "EUR"));
    }


    private Price getPrice() throws ParseException {

        return Price.builder()
                .productId(35555)
                .brandId(1)
                .startDate(DATE_FORMAT.parse("2020-06-14 00:00:00"))
                .endDate(DATE_FORMAT.parse("2020-06-15 00:00:00"))
                .build();
    }

    private Price getPriceWithNullProductId() throws ParseException {

        return Price.builder()
                .productId(null)
                .brandId(1)
                .startDate(DATE_FORMAT.parse("2020-06-14 00:00:00"))
                .endDate(DATE_FORMAT.parse("2020-06-15 00:00:00"))
                .build();
    }

    private Price getPriceWithNullBrandId() throws ParseException {

        return Price.builder()
                .productId(35555)
                .brandId(null)
                .startDate(DATE_FORMAT.parse("2020-06-14 00:00:00"))
                .endDate(DATE_FORMAT.parse("2020-06-15 00:00:00"))
                .build();
    }


    private Price getPriceWithInvalidDates() throws ParseException {

        return Price.builder()
                .productId(35555)
                .brandId(1)
                .startDate(DATE_FORMAT.parse("2020-06-18 00:00:00"))
                .endDate(DATE_FORMAT.parse("2020-06-15 00:00:00"))
                .build();
    }

    private List<PriceResponseDTO> getPricesResponseDTO() throws ParseException {

        return List.of(new PriceResponseDTO(35555, BRAND_RESPONSE_DTO, "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1, 50.0));
    }
}
