package com.application.inditex.prices.integration;

import com.application.inditex.prices.configuration.TestConfigurationProperties;
import com.inditex.prices.PricesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PricesApplication.class)
@Import(TestConfigurationProperties.class)
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ActiveProfiles("test")
public class PricesIntegrationTest {

    @Autowired
    private TestConfigurationProperties testConfigurationProperties;

    @Autowired
    private MockMvc mvc;

    @Test
    public void given_ProductQueryCustom1_getPriceByFilter_ReturnsPricesCustom1() throws Exception {

        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-14 10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14 00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"));
    }

    @Test
    public void given_ProductQueryCustom2_getPriceByFilter_ReturnsPricesCustom2() throws Exception {

        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-14 16:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("25.45"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14 15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-14 18:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"));

    }

    @Test
    public void given_ProductQueryCustom3_getPriceByFilter_ReturnsPricesCustom3() throws Exception {

        //  Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-14 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14 00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"));
    }

    @Test
    public void given_ProductQueryCustom4_getPriceByFilter_ReturnsPricesCustom4() throws Exception {

        //  Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-15 10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("30.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15 00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-15 11:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"));
    }

    @Test
    public void given_ProductQueryCustom5_getPriceByFilter_ReturnsPricesCustom5() throws Exception {

        //  Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15 16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"));
    }

    @Test
    public void given_ProductQueryWithOtherProductID_getPriceByFilter_ReturnsEmptyResponse() throws Exception {

        //  Test 6: Producto no encontrado

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "00000")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-10 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void given_BadUrl_getPriceByFilter_ReturnsNotFoundException() throws Exception {

        //  Test 8: Url malformada

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri() + "/prices")
                        .param("productId", "35555")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void given_ProductQueryWithNullProductId_getPriceByFilter_ThrowsException() throws Exception {

        //  Test 9: ProductId null

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void given_ProductQueryWithNullBrandId_getPriceByFilter_ThrowsException() throws Exception {

        //  Test 10: BrandId null

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35555")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void given_ProductQueryWithNullStartDate_getPriceByFilter_ThrowsException() throws Exception {

        //  Test 11: startDate null

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35555")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void given_ProductQueryWithBadFormatProductId_getPriceByFilter_ThrowsException() throws Exception {

        //  Test 12: Bad format productId

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "Bad format")
                        .param("brandId", "1")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void given_ProductQueryWithBadFormatBrandId_getPriceByFilter_ThrowsException() throws Exception {

        //  Test 13: Bad format brandId

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35555")
                        .param("brandId", "Bad format")
                        .param("startDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void given_ProductQueryWithBadFormatDate_getPriceByFilter_ThrowsException() throws Exception {

        //  Test 14: Bad format startDate

        mvc.perform(MockMvcRequestBuilders
                        .get(testConfigurationProperties.getUri())
                        .param("productId", "35555")
                        .param("brandId", "1")
                        .param("startDate", "Bad format")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

