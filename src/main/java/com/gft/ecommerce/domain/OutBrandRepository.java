package com.gft.ecommerce.domain;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;

import java.util.Optional;

public interface OutBrandRepository {
    Optional<BrandEntity> findByName(String name);
    BrandEntity save(BrandEntity brand);
}
