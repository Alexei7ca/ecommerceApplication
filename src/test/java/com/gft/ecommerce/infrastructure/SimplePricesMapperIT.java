package com.gft.ecommerce.infrastructure;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.domain.Price;
import com.gft.ecommerce.infrastructure.mapper.SimplePriceMapper;
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
        BrandEntity brand = new BrandEntity();
        brand.setId(1);
        brand.setName("ZARA");

        PriceEntity priceInfo = new PriceEntity();
        priceInfo.setBrand(brand);
        priceInfo.setStart(LocalDateTime.now());
        priceInfo.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo.setPriceListId(1);
        priceInfo.setProductId(35455);
        priceInfo.setPriority(1);
        priceInfo.setPrice(valueOf("50.00"));
        priceInfo.setCurrency("EUR");

        Price finalPrice = mapper.priceEntityToPriceDto(priceInfo);

        assertEquals(priceInfo.getBrand().getName(), finalPrice.getBrand());
        assertEquals(priceInfo.getStart(), finalPrice.getStart());
        assertEquals(priceInfo.getEnd(), finalPrice.getEnd());
        assertEquals(priceInfo.getPriceListId(), finalPrice.getPriceTariffId());
        assertEquals(priceInfo.getProductId(), finalPrice.getProductId());
        assertEquals(priceInfo.getPrice(), finalPrice.getPrice());
        assertEquals(priceInfo.getCurrency(), finalPrice.getCurrency());
    }


    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        BrandEntity brand = new BrandEntity();
        brand.setId(1);
        brand.setName("ZARA");

        Price finalPrice = new Price();
        finalPrice.setBrand(brand.getName());
        finalPrice.setStart(LocalDateTime.now());
        finalPrice.setEnd(LocalDateTime.now().plusDays(1));
        finalPrice.setPriceTariffId(1);
        finalPrice.setProductId(35455);
        finalPrice.setPrice(valueOf("50.00"));
        finalPrice.setCurrency("EUR");

        PriceEntity priceInfo = mapper.priceDtoToPriceEntity(finalPrice);

        assertNull(priceInfo.getBrand());
        assertEquals(finalPrice.getStart(), priceInfo.getStart());
        assertEquals(finalPrice.getEnd(), priceInfo.getEnd());
        assertEquals(finalPrice.getPriceTariffId(), priceInfo.getPriceListId());
        assertEquals(finalPrice.getProductId(), priceInfo.getProductId());
        assertEquals(finalPrice.getPrice(), priceInfo.getPrice());
        assertEquals(finalPrice.getCurrency(), priceInfo.getCurrency());
    }

}