package com.application.inditex.prices.service;

import com.inditex.prices.domain.model.*;
import com.inditex.prices.domain.model.Brand;
import com.inditex.prices.infraestructure.api.mappers.ProductMapper;
import com.inditex.prices.infraestructure.database.PricesAdapter;
import com.inditex.prices.infraestructure.database.PricesRepository;
import com.inditex.prices.infraestructure.database.entity.BrandVO;
import com.inditex.prices.infraestructure.database.entity.PricesVO;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesAdapterTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final ProductQuery PRICE_DTO ,INVALID_PRICE_DTO;


    static {
        try {
            PRICE_DTO = new ProductQuery(35555, 1, DATE_FORMAT.parse("2020-06-14 00:00:00"));
            INVALID_PRICE_DTO = new ProductQuery(null, 1,DATE_FORMAT.parse("2020-06-14 00:00:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @InjectMocks
    PricesAdapter pricesAdapter;

    @Mock
    PricesRepository pricesRepositoryMock;

    @Mock
    ProductMapper productMapperMock;

    @Test
    public void givenCorrectParams_getPricesByService_getPriceResponseDTO() throws ParseException {

        when(productMapperMock.mapToProducts(anyList())).thenReturn(getProducts());

        when(pricesRepositoryMock.findByPriceDTOWithDates(any(ProductQuery.class))).thenReturn(getPricesVO());

        List<Product> productsByFilter = pricesAdapter.getPricesByFilter(PRICE_DTO);

        assertEquals(1, productsByFilter.size());

        assertThat(productsByFilter.get(0).getProductId()).isEqualTo(35555);
        assertThat(productsByFilter.get(0).getBrand().getBrandId()).isEqualTo(1);
        assertThat(productsByFilter.get(0).getBrand().getName()).isEqualTo("test");
        assertThat(productsByFilter.get(0).getStartDate()).isEqualTo("2020-06-14 00:00:00");
        assertThat(productsByFilter.get(0).getEndDate()).isEqualTo("2020-06-15 00:00:00");
        assertThat(productsByFilter.get(0).getPriceList()).isEqualTo(1);
        assertThat(productsByFilter.get(0).getPrice()).isEqualTo(50.0);

    }

    @Test
    public void givenNullParamProductId_getPricesByService_throwNullValuesException() {

        doThrow(new IllegalArgumentException("productId cannot be null")).when(productMapperMock).mapToProducts(any(List.class));

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO));

        assertEquals("productId cannot be null", illegalArgumentException.getMessage());

    }

    @Test
    public void givenNullParamBrandId_getPricesByService_throwNullValuesException() {

        doThrow(new IllegalArgumentException("brandId cannot be null")).when(productMapperMock).mapToProducts(any(List.class));

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO));

        assertEquals("brandId cannot be null", illegalArgumentException.getMessage());

    }

    @Test
    public void givenBadDatesParams_getPricesByService_throwInvalidDatesException() {

        doThrow(new IllegalArgumentException("start date must be greater than end date")).when(productMapperMock).mapToProducts(any(List.class));

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> pricesAdapter.getPricesByFilter(INVALID_PRICE_DTO));

        assertEquals("start date must be greater than end date", illegalArgumentException.getMessage());

    }

    private List<PricesVO> getPricesVO() throws ParseException {

        return Collections.singletonList(new PricesVO(35555, 1,  new BrandVO(1, "ZARA"),
                DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 1,50.0, "EUR"));
    }

    private List<Product> getProducts() throws ParseException {

        return Collections.singletonList(new Product(35555, new Brand(1,"test") ,DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"),1,50.0));

    }
}
