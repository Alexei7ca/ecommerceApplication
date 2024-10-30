package com.gft.ecommerce.infrastructure.adapter.out;

import com.gft.ecommerce.domain.OutBrandRepository;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BrandRepositoryAdapter implements OutBrandRepository {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandRepositoryAdapter(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<BrandEntity> findByName(String name) {
        return Optional.ofNullable(brandRepository.findByName(name));
    }

    @Override
    public BrandEntity save(BrandEntity brand) {
        return brandRepository.save(brand);
    }
}
