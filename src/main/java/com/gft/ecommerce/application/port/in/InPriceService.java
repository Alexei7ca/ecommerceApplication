package com.gft.ecommerce.application.port.in;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface InPriceService {
    List<PriceEntity> getAllPrices();

    PriceEntity getPriceById(Integer id);

    PriceEntity getPrice(LocalDateTime date, BrandEntity brand, int productId);
}

