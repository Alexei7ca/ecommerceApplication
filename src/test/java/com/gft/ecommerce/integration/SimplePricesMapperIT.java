package com.gft.ecommerce.integration;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.domain.Price;
import com.gft.ecommerce.dto.PriceDto;
import com.gft.ecommerce.mapper.SimplePriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static java.lang.Double.valueOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplePricesMapperIT {

    private SimplePriceMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(SimplePriceMapper.class);
    }

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        Price priceInfo = new Price();
        priceInfo.setBrand(brand);
        priceInfo.setStart(LocalDateTime.now());
        priceInfo.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo.setPriceListId(1);
        priceInfo.setProductId(35455);
        priceInfo.setPriority(1);
        priceInfo.setPrice(valueOf("50.00"));
        priceInfo.setCurrency("EUR");

        PriceDto finalPriceDto = mapper.priceToPriceDto(priceInfo);

        assertEquals(priceInfo.getBrand().getName(), finalPriceDto.getBrand());
        assertEquals(priceInfo.getStart(), finalPriceDto.getStart());
        assertEquals(priceInfo.getEnd(), finalPriceDto.getEnd());
        assertEquals(priceInfo.getPriceListId(), finalPriceDto.getPriceTariffId());
        assertEquals(priceInfo.getProductId(), finalPriceDto.getProductId());
        assertEquals(priceInfo.getPrice(), finalPriceDto.getPrice());
        assertEquals(priceInfo.getCurrency(), finalPriceDto.getCurrency());
    }


    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        PriceDto finalPriceDto = new PriceDto();
        finalPriceDto.setBrand(brand.getName());
        finalPriceDto.setStart(LocalDateTime.now());
        finalPriceDto.setEnd(LocalDateTime.now().plusDays(1));
        finalPriceDto.setPriceTariffId(1);
        finalPriceDto.setProductId(35455);
        finalPriceDto.setPrice(valueOf("50.00"));
        finalPriceDto.setCurrency("EUR");

        Price priceInfo = mapper.priceDtoToPrice(finalPriceDto);

        assertNull(priceInfo.getBrand());
        assertEquals(finalPriceDto.getStart(), priceInfo.getStart());
        assertEquals(finalPriceDto.getEnd(), priceInfo.getEnd());
        assertEquals(finalPriceDto.getPriceTariffId(), priceInfo.getPriceListId());
        assertEquals(finalPriceDto.getProductId(), priceInfo.getProductId());
        assertEquals(finalPriceDto.getPrice(), priceInfo.getPrice());
        assertEquals(finalPriceDto.getCurrency(), priceInfo.getCurrency());
    }

}