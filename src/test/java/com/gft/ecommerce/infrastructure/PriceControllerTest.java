package com.gft.ecommerce.infrastructure;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.application.service.InBrandServiceImpl;
import com.gft.ecommerce.application.service.InPriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.valueOf;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InPriceServiceImpl pricesService;

    @MockBean
    private InBrandServiceImpl brandService;

    private PriceEntity priceInfo1;
    private PriceEntity priceInfo2;
    private BrandEntity brand;

    @BeforeEach
    public void setup() {
        brand = new BrandEntity();
        brand.setId(1);
        brand.setName("Brand1");

        priceInfo1 = new PriceEntity();
        priceInfo1.setBrand(brand);
        priceInfo1.setPriceListId(10);
        priceInfo1.setStart(LocalDateTime.now());
        priceInfo1.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo1.setProductId(4444);
        priceInfo1.setPrice(valueOf("123.45"));
        priceInfo1.setCurrency("EUR");

        priceInfo2 = new PriceEntity();
        priceInfo2.setBrand(brand);
        priceInfo2.setPriceListId(11);
        priceInfo2.setStart(LocalDateTime.now().plusDays(2));
        priceInfo2.setEnd(LocalDateTime.now().plusDays(3));
        priceInfo2.setProductId(5555);
        priceInfo2.setPrice(valueOf("135.45"));
        priceInfo2.setCurrency("EUR");
    }

    @Test
    public void whenGetAllPrices_thenReturnJsonArray() throws Exception {
        List<PriceEntity> allPrices = Arrays.asList(priceInfo1, priceInfo2);

        given(pricesService.getAllPrices()).willReturn(allPrices);

        mockMvc.perform(get("/api/prices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].priceTariffId").value(priceInfo1.getPriceListId()))
                .andExpect(jsonPath("$[0].brand").value(brand.getName()))
                .andExpect(jsonPath("$[1].priceTariffId").value(priceInfo2.getPriceListId()))
                .andExpect(jsonPath("$[1].brand").value(brand.getName()));
    }

    @Test
    public void whenGetPriceById_thenReturnPriceDto() throws Exception {
        given(pricesService.getPriceById(priceInfo1.getPriceListId())).willReturn(priceInfo1);

        mockMvc.perform(get("/api/prices/" + priceInfo1.getPriceListId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceTariffId").value(priceInfo1.getPriceListId()));
    }

    @Test
    public void whenGetPrice_thenReturnPriceDto() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        given(brandService.getBrandByName(brand.getName())).willReturn(brand);
        given(pricesService.getPrice(now, brand, priceInfo1.getProductId())).willReturn(priceInfo1);

        mockMvc.perform(get("/api/prices/price")
                        .param("date", now.toString())
                        .param("product_id", String.valueOf(priceInfo1.getProductId()))
                        .param("brand", brand.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceTariffId").value(priceInfo1.getPriceListId()))
                .andExpect(jsonPath("$.brand").value(brand.getName()));
    }

}
