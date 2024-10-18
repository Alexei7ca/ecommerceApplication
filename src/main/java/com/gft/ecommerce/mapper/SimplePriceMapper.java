package com.gft.ecommerce.mapper;

import com.gft.ecommerce.domain.Price;
import com.gft.ecommerce.dto.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimplePriceMapper {

    @Mapping(target = "brand", source = "brand.name")
    @Mapping(target = "priceTariffId", source = "priceListId")
    PriceDto priceToPriceDto(Price priceInfo);

    @Mapping(target = "priceListId", source = "priceTariffId")
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "priority", ignore = true)
    Price priceDtoToPrice(PriceDto priceDto);
}
