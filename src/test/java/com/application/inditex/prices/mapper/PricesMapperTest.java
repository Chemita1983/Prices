package com.application.inditex.prices.mapper;

import com.application.inditex.prices.domain.Price;
import com.application.inditex.prices.entity.BrandVO;
import com.application.inditex.prices.entity.PricesVO;
import com.application.inditex.prices.input.PriceDTO;
import com.application.inditex.prices.output.BrandResponseDTO;
import com.application.inditex.prices.output.PriceResponseDTO;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesMapperTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final PriceDTO PRICE_DTO;

    private static final BrandResponseDTO BRAND_RESPONSE_DTO;

    static {
        PRICE_DTO = new PriceDTO(35555, "2020-06-14 00:00:00", "2020-06-15 00:00:00", 1);

        BRAND_RESPONSE_DTO = new BrandResponseDTO(1, "ZARA");
    }

    @InjectMocks
    PricesMapper pricesMapper;

    @Mock
    BrandMapper brandMapper;


    @Test
    public void given_PricesDto_convertToPrice_thenReturnPrice() throws ParseException {

        Price price = pricesMapper.convertToPrice(PRICE_DTO);

        assertThat(price.getProductId()).isEqualTo(35555);
        assertThat(price.getBrandId()).isEqualTo(1);
        assertThat(price.getStartDate()).isEqualTo(DATE_FORMAT.parse("2020-06-14 00:00:00"));
        assertThat(price.getEndDate()).isEqualTo(DATE_FORMAT.parse("2020-06-15 00:00:00"));

    }

    @Test
    public void given_PricesVO_convertToPriceResponseDTO_thenReturnPriceResponse() throws ParseException {

        when(brandMapper.convertToBrandResponseDTO(any(BrandVO.class))).thenReturn(BRAND_RESPONSE_DTO);

        List<PriceResponseDTO> prices = pricesMapper.convertToPriceResponseDto(getPricesVO());

        assertThat(prices.size()).isEqualTo(1);
        assertThat(prices.get(0).getProductId()).isEqualTo(35555);
        assertThat(prices.get(0).getPriceList()).isEqualTo(1);
        assertThat(prices.get(0).getBrand()).isEqualTo(BRAND_RESPONSE_DTO);
        assertThat(prices.get(0).getStartDate()).isEqualTo("2020-06-14 00:00:00");
        assertThat(prices.get(0).getEndDate()).isEqualTo("2020-06-15 00:00:00");
    }

    private List<PricesVO> getPricesVO() throws ParseException {

        PricesVO pricesVO = new PricesVO(1, 35555, 1, new BrandVO(1, "ZARA"),
                DATE_FORMAT.parse("2020-06-14 00:00:00"), DATE_FORMAT.parse("2020-06-15 00:00:00"), 1, 50.0, "EUR");

        return List.of(pricesVO);

    }

}
