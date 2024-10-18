package com.gft.ecommerce.repository;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.domain.Price;

import java.time.LocalDateTime;

public interface CustomPriceRepository {
    Price findFirstPriceByProductBrandAndDate(LocalDateTime date, Brand brand, int productId);
}

