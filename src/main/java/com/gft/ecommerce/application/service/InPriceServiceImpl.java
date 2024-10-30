package com.gft.ecommerce.application.service;

import com.gft.ecommerce.domain.InPriceService;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.infrastructure.repository.PriceRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
public class InPriceServiceImpl implements InPriceService {

    private final PriceRepository priceRepository;

    public InPriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<PriceEntity> getAllPrices() {
        return priceRepository.findAll();
    }

    public PriceEntity getPriceById(Integer id) {
        return priceRepository.findById(id).orElseThrow();
    }

    public PriceEntity getPrice(LocalDateTime date, BrandEntity brand, int productId) {
        return priceRepository.findFirstPriceByProductBrandAndDate(date, brand, productId);
    }
}

