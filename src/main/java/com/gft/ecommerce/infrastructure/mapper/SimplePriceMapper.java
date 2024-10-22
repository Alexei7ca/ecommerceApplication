package com.gft.ecommerce.infrastructure.mapper;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimplePriceMapper {

    @Mapping(target = "brand", source = "brand.name")
    @Mapping(target = "priceTariffId", source = "priceListId")
    Price priceEntityToPriceDto(PriceEntity priceInfo);

    @Mapping(target = "priceListId", source = "priceTariffId")
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "priority", ignore = true)
    PriceEntity priceDtoToPriceEntity(Price price);
}
