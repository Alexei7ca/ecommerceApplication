package com.gft.ecommerce.service;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.repository.BrandRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandsRepository;

    public BrandServiceImpl(BrandRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public List<Brand> getAllBrands() {
        return brandsRepository.findAll();
    }

    public Brand getBrandByName(String name) {
        return brandsRepository.findByName(name);
    }

    public Brand saveBrand(Brand brand) {
        if (brandsRepository.findByName(brand.getName()) != null) {
            throw new IllegalArgumentException("Brand with name " + brand.getName() + " already exists");
        }
        return brandsRepository.save(brand);
    }

}

