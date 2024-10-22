package com.gft.ecommerce.infrastructure.mapper;


import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.domain.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleBrandMapper {

    Brand brandEntityToBrandDto(BrandEntity brand);

    BrandEntity brandDtoToBrandEntity(Brand brand);
}
