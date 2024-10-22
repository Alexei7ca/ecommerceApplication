package com.gft.ecommerce.infrastructure;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.infrastructure.mapper.SimpleBrandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBrandMapperIT {

    private SimpleBrandMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(SimpleBrandMapper.class);
    }

    @Test
    public void givenBrandEntityToBrandDto_whenMaps_thenCorrect() {
        BrandEntity brand = new BrandEntity();
        brand.setId(1);
        brand.setName("ZARA");

        Brand brandDto = mapper.brandEntityToBrandDto(brand);

        assertEquals(brand.getId(), brandDto.getId());
        assertEquals(brand.getName(), brandDto.getName());
    }

    @Test
    public void givenBrandDtoToBrand_Entity_whenMaps_thenCorrect() {
        Brand brandDto = new Brand();
        brandDto.setId(1);
        brandDto.setName("ZARA");

        BrandEntity brand = mapper.brandDtoToBrandEntity(brandDto);

        assertEquals(brandDto.getId(), brand.getId());
        assertEquals(brandDto.getName(), brand.getName());
    }

    @Test
    public void givenNullBrand_whenBrandEntityToBrandDto_thenNullReturned() {
        Brand brand = mapper.brandEntityToBrandDto(null);

        assertNull(brand);
    }

    @Test
    public void givenNullBrandDto_whenBrandDtoToBrand_Entity_thenNullReturned() {
        BrandEntity brand = mapper.brandDtoToBrandEntity(null);

        assertNull(brand);
    }
}

