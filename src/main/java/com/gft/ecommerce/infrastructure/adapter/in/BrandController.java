package com.gft.ecommerce.infrastructure.adapter.in;

import com.gft.ecommerce.domain.InBrandService;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.infrastructure.mapper.SimpleBrandMapper;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@FieldDefaults(makeFinal = true)
public class BrandController {

    private InBrandService brandService;
    private SimpleBrandMapper mapper;

    public BrandController(InBrandService brandService, SimpleBrandMapper mapper) {
        this.brandService = brandService;
        this.mapper = mapper;
    }


    @GetMapping()
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands().stream().map(mapper::brandEntityToBrandDto).toList();
    }

    @GetMapping("/{brandName}")
    public Brand getBrandByName(@PathVariable String brandName) {
        BrandEntity brand = brandService.getBrandByName(brandName);
        return mapper.brandEntityToBrandDto(brand);
    }

    @PostMapping()
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brandDto) {
        try {
            BrandEntity brand = mapper.brandDtoToBrandEntity(brandDto);
            BrandEntity savedBrand = brandService.saveBrand(brand);
            return new ResponseEntity<>(mapper.brandEntityToBrandDto(savedBrand), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}