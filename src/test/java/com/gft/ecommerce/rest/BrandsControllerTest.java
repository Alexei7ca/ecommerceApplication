package com.gft.rest;

import com.decskill.ecommerce.domain.Brand;
import com.decskill.ecommerce.service.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setup() {
        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setName("Brand1");

        Brand brand2 = new Brand();
        brand2.setId(2);
        brand2.setName("Brand2");

        List<Brand> allBrands = Arrays.asList(brand1, brand2);
        given(brandService.getAllBrands()).willReturn(allBrands);
    }

    @Test
    public void whenGetAllBrands_thenReturnJsonArray() throws Exception {
        mockMvc.perform(get("/api/brands"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Brand1"))
                .andExpect(jsonPath("$[1].name").value("Brand2"));
    }
}
