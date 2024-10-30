package com.gft.ecommerce.domain;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OutPriceRepository {
    List<PriceEntity> findPricesByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId);
    PriceEntity findFirstPriceByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId);
}
