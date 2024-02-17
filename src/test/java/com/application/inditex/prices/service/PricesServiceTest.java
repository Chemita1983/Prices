package com.application.inditex.prices.service;

import com.inditex.prices.domain.product.brand.Brand;
import com.inditex.prices.domain.product.brand.BrandId;
import com.inditex.prices.domain.product.brand.Name;
import com.inditex.prices.domain.product.*;
import com.inditex.prices.infraestructure.PricesAdapter;
import com.inditex.prices.infraestructure.entity.BrandVO;
import com.inditex.prices.infraestructure.entity.PricesVO;
import com.inditex.prices.domain.exceptions.InvalidDatesException;
import com.inditex.prices.domain.exceptions.NullValueException;
import com.inditex.prices.application.obtainPrice.inbound.PriceDTO;
import com.inditex.prices.infraestructure.mappers.ProductMapper;
import com.inditex.prices.infraestructure.PricesRepository;
import com.inditex.prices.infraestructure.ProductValidator;
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
    ProductValidator productValidatorMock;

    @Mock
    PricesRepository pricesRepositoryMock;

    @Mock
    ProductMapper productMapperMock;

    @Test
    public void givenCorrectParams_getPricesByService_getPriceResponseDTO() throws ParseException {

        when(productMapperMock.mapToPrice(any(PricesVO.class))).thenReturn(getProduct());

        when(pricesRepositoryMock.findByPriceDTOWithDates(any(PriceDTO.class))).thenReturn(getPricesVO());

        List<Product> pricesByFilter = pricesAdapter.getPricesByFilter(PRICE_DTO);

        assertEquals(1, pricesByFilter.size());

        assertThat(pricesByFilter.get(0).getProductId().value()).isEqualTo(35555);
        assertThat(pricesByFilter.get(0).getBrand().getBrandId().value()).isEqualTo(1);
        assertThat(pricesByFilter.get(0).getBrand().getName().value()).isEqualTo("test");
        assertThat(pricesByFilter.get(0).getStartDate().value()).isEqualTo("2020-06-14 00:00:00");
        assertThat(pricesByFilter.get(0).getEndDate().value()).isEqualTo("2020-06-15 00:00:00");
        assertThat(pricesByFilter.get(0).getPriceList().value()).isEqualTo(1);
        assertThat(pricesByFilter.get(0).getPrice().value()).isEqualTo(50.0);

    }

    @Test
    public void givenNullParamProductId_getPricesByService_throwNullValuesException() throws InvalidDatesException, ParseException, NullValueException {

        doThrow(new NullValueException("productId cannot be null")).when(productValidatorMock).validInputPrice(any(PriceDTO.class));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO);
        });

        assertEquals("productId cannot be null", nullValueException.getMessage());

    }

    @Test
    public void givenNullParamBrandId_getPricesByService_throwNullValuesException() throws InvalidDatesException, ParseException, NullValueException {

        doThrow(new NullValueException("brandId cannot be null")).when(productValidatorMock).validInputPrice(any(PriceDTO.class));

        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO);
        });

        assertEquals("brandId cannot be null", nullValueException.getMessage());

    }

    @Test
    public void givenBadDatesParams_getPricesByService_throwInvalidDatesException() throws InvalidDatesException, NullValueException {

        doThrow(new InvalidDatesException("start date must be greater than end date")).when(productValidatorMock).validInputPrice(any(PriceDTO.class));

        InvalidDatesException invalidDatesException = Assertions.assertThrows(InvalidDatesException.class, () -> {
            pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO);
        });

        assertEquals("start date must be greater than end date", invalidDatesException.getMessage());

    }

    private List<PricesVO> getPricesVO() throws ParseException {

        return Collections.singletonList(new PricesVO(35555, 1,  new BrandVO(1, "ZARA"),
                DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 1,50.0, "EUR"));
    }


    private Product getProduct() throws ParseException {

        return new Product(
                    new ProductId(35555),
                    new Brand(new BrandId(1), new Name("test")),
                    new StartDate(DATE_FORMAT.parse("2020-06-14 00:00:00")),
                    new EndDate(DATE_FORMAT.parse("2020-06-15 00:00:00")),
                    new PriceList(1),
                    new Amount(50.0)
        );

    }
}
