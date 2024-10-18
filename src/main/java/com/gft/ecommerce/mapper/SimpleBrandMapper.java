package com.gft.ecommerce.mapper;


import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.dto.BrandDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleBrandMapper {

    BrandDto brandToBrandDto(Brand brand);

    Brand brandDtoToBrand(BrandDto brandDto);
}
