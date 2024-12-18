package com.gft.ecommerce.domain;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;

import java.util.List;

public interface InBrandService {

    List<BrandEntity> getAllBrands();

    BrandEntity getBrandByName(String name);

    BrandEntity saveBrand(BrandEntity brand);
}
