package com.gft.ecommerce.rest;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.dto.BrandDto;
import com.gft.ecommerce.mapper.SimpleBrandMapper;
import com.gft.ecommerce.service.BrandServiceImpl;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{brandName}")
    public BrandDto getBrandByName(@PathVariable String brandName) {
        Brand brand = brandService.getBrandByName(brandName);
        return mapper.brandToBrandDto(brand);
    }

    @PostMapping()
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto) {
        try {
            Brand brand = mapper.brandDtoToBrand(brandDto);
            Brand savedBrand = brandService.saveBrand(brand);
            return new ResponseEntity<>(mapper.brandToBrandDto(savedBrand), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}