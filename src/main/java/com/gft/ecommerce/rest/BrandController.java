package com.gft.rest;

import com.gft.ecommerce.dto.BrandDto;
import com.gft.ecommerce.mapper.SimpleBrandMapper;
import com.gft.ecommerce.service.BrandServiceImpl;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@FieldDefaults(makeFinal = true)
public class BrandController {

    private BrandServiceImpl brandService;
    private SimpleBrandMapper mapper;

    public BrandController(BrandServiceImpl brandService, SimpleBrandMapper mapper) {
        this.brandService = brandService;
        this.mapper = mapper;
    }


    @GetMapping()
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands().stream().map(mapper::brandToBrandDto).toList();
    }
}