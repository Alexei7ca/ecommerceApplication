package com.gft.integration;

import com.decskill.ecommerce.domain.Brand;
import com.decskill.ecommerce.dto.BrandDto;
import com.decskill.ecommerce.mapper.SimpleBrandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNull;

public class SimpleBrandMapperIT {

    private SimpleBrandMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(SimpleBrandMapper.class);
    }

    @Test
    public void givenBrandToBrandDto_whenMaps_thenCorrect() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        BrandDto brandDto = mapper.brandToBrandDto(brand);

        assertEquals(brand.getId(), brandDto.getId());
        assertEquals(brand.getName(), brandDto.getName());
    }

    @Test
    public void givenBrandDtoToBrand_whenMaps_thenCorrect() {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(1);
        brandDto.setName("ZARA");

        Brand brand = mapper.brandDtoToBrand(brandDto);

        assertEquals(brandDto.getId(), brand.getId());
        assertEquals(brandDto.getName(), brand.getName());
    }

    @Test
    public void givenNullBrand_whenBrandToBrandDto_thenNullReturned() {
        BrandDto brandDto = mapper.brandToBrandDto(null);

        assertNull(brandDto);
    }

    @Test
    public void givenNullBrandDto_whenBrandDtoToBrand_thenNullReturned() {
        Brand brand = mapper.brandDtoToBrand(null);

        assertNull(brand);
    }
}

