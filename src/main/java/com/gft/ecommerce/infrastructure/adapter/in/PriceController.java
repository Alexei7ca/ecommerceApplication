package com.gft.ecommerce.infrastructure.adapter.in;

import com.gft.ecommerce.domain.InBrandService;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.domain.Price;
import com.gft.ecommerce.shared.exception.NotFoundException;
import com.gft.ecommerce.infrastructure.mapper.SimplePriceMapper;
import com.gft.ecommerce.domain.InPriceService;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
@Slf4j
@FieldDefaults(makeFinal = true)
public class PriceController {

    private InPriceService pricesService;
    private SimplePriceMapper mapper;
    private InBrandService brandService;

    public PriceController(InPriceService pricesService, SimplePriceMapper mapper, InBrandService brandService) {
        this.pricesService = pricesService;
        this.mapper = mapper;
        this.brandService = brandService;
    }

    @GetMapping()
    public List<Price> getAllPrices() {
        log.info("getAllPrices method was called");
        return pricesService.getAllPrices().stream().map(mapper::priceEntityToPriceDto).toList();
    }

    @GetMapping("/{priceId}")
    public Price getPriceById(@PathVariable(value = "priceId") int priceId) {
        log.info("getPriceById method was called");
        PriceEntity price = pricesService.getPriceById(priceId);
        return mapper.priceEntityToPriceDto(price);
    }

    @GetMapping("/price")
    public Price getPrice(@RequestParam(name = "date") LocalDateTime date,
                          @RequestParam(name = "product_id") Integer productId,
                          @RequestParam(name = "brand") String brandName) {
        log.info("getPrice method was called");

        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("product_id is not valid");
        }

        BrandEntity brand = brandService.getBrandByName(brandName);
        if (brand == null) {
            throw new NotFoundException("Brand " + brandName + " not found.");
        }

        PriceEntity info = pricesService.getPrice(date, brand, productId);
        if (info == null) {
            throw new NotFoundException("Price not found.");
        }
        return mapper.priceEntityToPriceDto(info);
    }

}
