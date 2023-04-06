package com.application.inditex.prices.integration;

import com.application.inditex.prices.configuration.TestConfigurationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ActiveProfiles("test")
public class PricesControllerIntegrationTest {

    private static final String PRODUCT_ID = "35455";

    private static final String OTHER_PRODUCT_ID = "35456";

    @Autowired
    private TestConfigurationProperties testConfigurationProperties;

    @Autowired
    private MockMvc mvc;

    @Test
    public void given_PricesDTOCustom1_getPriceByFilter_ReturnsPricesCustom1() throws Exception {

        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-14 10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", hasSize(3)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value("25.45"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].startDate").value("2020-06-14 15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.name").value("ZARA"))


                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].priceList").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price").value("30.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].startDate").value("2020-06-15 00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brand.name").value("ZARA"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].priceList").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].price").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].startDate").value("2020-06-15 16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].brand.name").value("ZARA"));
    }

    @Test
    public void given_PricesDTOCustom2_getPriceByFilter_ReturnsPricesCustom2() throws Exception {

        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-14 16:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", hasSize(2)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value("30.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].startDate").value("2020-06-15 00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.name").value("ZARA"))


                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].priceList").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].startDate").value("2020-06-15 16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brand.name").value("ZARA"));

    }

    @Test
    public void given_PricesDTOCustom3_getPriceByFilter_ReturnsPricesCustom3() throws Exception {

        //  Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-14 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", hasSize(2)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value("30.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].startDate").value("2020-06-15 00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.name").value("ZARA"))


                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].priceList").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].startDate").value("2020-06-15 16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brand.name").value("ZARA"));

    }

    @Test
    public void given_PricesDTOCustom4_getPriceByFilter_ReturnsPricesCustom4() throws Exception {

        //  Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-15 10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", hasSize(1)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].startDate").value("2020-06-15 16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand.name").value("ZARA"));
    }

    @Test
    public void given_PricesDTOCustom5_getPriceByFilter_ReturnsPricesCustom5() throws Exception {

        //  Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isEmpty());
    }

    @Test
    public void given_PricesDTOCustomWithMissingProductID_getPriceByFilter_ReturnsEmpty() throws Exception {

        //  Test 6: Producto no encontrado

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + OTHER_PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-10 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isEmpty());
    }

    @Test
    public void given_PricesDTOCustomWithBadDates_getPriceByFilter_ReturnsBadRequestException() throws Exception {

        //  Test 7: Fechas invalidas

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .param("endDate", "2020-06-15 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("start date must be greater than end date"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isEmpty());
    }


    @Test
    public void given_BadUrl_getPriceByFilter_ReturnsNotFoundException() throws Exception {

        //  Test 8: Url malformada

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + "/badUrl/" + PRODUCT_ID)
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}

