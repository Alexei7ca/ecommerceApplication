package com.gft.service;

import com.gft.ecommerce.domain.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();

    Brand getBrandByName(String name);
}
