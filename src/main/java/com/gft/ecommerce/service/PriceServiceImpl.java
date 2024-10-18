package com.gft.service;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.domain.Price;
import com.gft.ecommerce.repository.PriceRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public Price getPriceById(Integer id) {
        return priceRepository.findById(id).orElseThrow();
    }

    public Price getPrice(LocalDateTime date, Brand brand, int productId) {
        return priceRepository.findFirstPriceByProductBrandAndDate(date, brand, productId);
    }
}

