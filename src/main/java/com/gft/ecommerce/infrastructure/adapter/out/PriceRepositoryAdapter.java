package com.gft.ecommerce.infrastructure.adapter.out;

import com.gft.ecommerce.domain.OutPriceRepository;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.infrastructure.repository.CustomPriceRepositoryImpl;
import com.gft.ecommerce.infrastructure.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceRepositoryAdapter implements OutPriceRepository {

    private final PriceRepository priceRepository;
    private final CustomPriceRepositoryImpl customPriceRepositoryImpl;

    @Autowired
    public PriceRepositoryAdapter(PriceRepository priceRepository, CustomPriceRepositoryImpl customPriceRepositoryImpl) {
        this.priceRepository = priceRepository;
        this.customPriceRepositoryImpl = customPriceRepositoryImpl;
    }

    @Override
    public List<PriceEntity> findPricesByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId) {
        return priceRepository.findPricesByProductBrandAndDate(date, brand, productId);
    }

    @Override
    public PriceEntity findFirstPriceByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId) {
        return customPriceRepositoryImpl.findFirstPriceByProductBrandAndDate(date, brand, productId);
    }
}
