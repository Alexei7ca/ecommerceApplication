package com.gft.ecommerce.infrastructure.repository;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;

import java.time.LocalDateTime;

public interface CustomPriceRepository {
    PriceEntity findFirstPriceByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId);
}

