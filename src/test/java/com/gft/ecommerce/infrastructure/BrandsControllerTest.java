package com.gft.ecommerce.infrastructure;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.application.service.InBrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InBrandServiceImpl brandService;

    @BeforeEach
    public void setup() {
        BrandEntity brand1 = new BrandEntity();
        brand1.setId(1);
        brand1.setName("Brand1");

        BrandEntity brand2 = new BrandEntity();
        brand2.setId(2);
        brand2.setName("Brand2");

        List<BrandEntity> allBrands = Arrays.asList(brand1, brand2);
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

    @Test
    public void whenGetBrandByName_thenReturnBrand() throws Exception {
        BrandEntity brand1 = new BrandEntity();
        brand1.setId(1);
        brand1.setName("Brand1");
        given(brandService.getBrandByName("Brand1")).willReturn(brand1);

        mockMvc.perform(get("/api/brands/Brand1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Brand1"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void whenSaveBrand_thenReturnCreatedBrand() throws Exception {
        BrandEntity newBrand = new BrandEntity();
        newBrand.setId(3);
        newBrand.setName("Brand3");

        Brand newBrandDto = new Brand();
        newBrandDto.setId(3);
        newBrandDto.setName("Brand3");

        given(brandService.saveBrand(any(BrandEntity.class))).willReturn(newBrand);

        mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 3, \"name\": \"Brand3\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Brand3"))
                .andExpect(jsonPath("$.id").value(3));
    }
}
