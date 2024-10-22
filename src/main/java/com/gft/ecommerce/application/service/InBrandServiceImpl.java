package com.gft.ecommerce.application.service;

import com.gft.ecommerce.application.port.in.InBrandService;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.repository.BrandRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
public class InBrandServiceImpl implements InBrandService {
    private BrandRepository brandsRepository;

    public InBrandServiceImpl(BrandRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public List<BrandEntity> getAllBrands() {
        return brandsRepository.findAll();
    }

    public BrandEntity getBrandByName(String name) {
        return brandsRepository.findByName(name);
    }

    public BrandEntity saveBrand(BrandEntity brand) {
        if (brandsRepository.findByName(brand.getName()) != null) {
            throw new IllegalArgumentException("Brand with name " + brand.getName() + " already exists");
        }
        return brandsRepository.save(brand);
    }

}

