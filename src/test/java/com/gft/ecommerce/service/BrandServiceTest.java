package com.gft.ecommerce.service;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BrandServiceTest {

    @Mock
    private BrandRepository brandsRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void whenFindAll_thenReturnBrandList() {
        Brand brand1 = new Brand();
        brand1.setId(5);
        brand1.setName("Brand1");
        Brand brand2 = new Brand();
        brand1.setId(6);
        brand1.setName("Brand2");

        when(brandsRepository.findAll()).thenReturn(Arrays.asList(brand1, brand2));

        List<Brand> foundBrands = brandService.getAllBrands();

        assertThat(foundBrands.size()).isEqualTo(2);
        assertThat(foundBrands.get(0).getId()).isEqualTo(brand1.getId());
        assertThat(foundBrands.get(0).getName()).isEqualTo(brand1.getName());
        assertThat(foundBrands.get(1).getId()).isEqualTo(brand2.getId());
        assertThat(foundBrands.get(1).getName()).isEqualTo(brand2.getName());
    }

    @Test
    public void whenFindAllAndBrandTableIsEmpty_thenReturnEmptyList() {
        when(brandsRepository.findAll()).thenReturn(List.of());

        List<Brand> foundBrands = brandService.getAllBrands();

        assertThat(foundBrands).isEmpty();
    }

    @Test
    public void whenFindByName_thenReturnBrand() {
        String name = "Brand 1";
        Integer id = 5;
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);

        when(brandsRepository.findByName(name)).thenReturn(brand);

        Brand foundBrand = brandService.getBrandByName(name);

        assertThat(foundBrand).isNotNull();
        assertThat(foundBrand.getId()).isEqualTo(id);
        assertThat(foundBrand.getName()).isEqualTo(name);
    }

    @Test
    public void whenFindByNameCannotFindBrand_thenReturnNull() {
        String name = "Non-existent Brand";

        when(brandsRepository.findByName(name)).thenReturn(null);

        Brand foundBrand = brandService.getBrandByName(name);

        assertThat(foundBrand).isNull();
    }

}
