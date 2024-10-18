package com.gft.rest;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.domain.Price;
import com.gft.ecommerce.dto.PriceDto;
import com.gft.ecommerce.exception.NotFoundException;
import com.gft.ecommerce.mapper.SimplePriceMapper;
import com.gft.ecommerce.service.BrandServiceImpl;
import com.gft.ecommerce.service.PriceService;
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

    private PriceService pricesService;
    private SimplePriceMapper mapper;
    private BrandServiceImpl brandService;

    public PriceController(PriceService pricesService, SimplePriceMapper mapper, BrandServiceImpl brandService) {
        this.pricesService = pricesService;
        this.mapper = mapper;
        this.brandService = brandService;
    }

    @GetMapping()
    public List<PriceDto> getAllPrices() {
        log.info("getAllPrices method was called");
        return pricesService.getAllPrices().stream().map(mapper::priceToPriceDto).toList();
    }

    @GetMapping("/{priceId}")
    public PriceDto getPriceById(@PathVariable(value = "priceId") int priceId) {
        log.info("getPriceById method was called");
        Price price = pricesService.getPriceById(priceId);
        return mapper.priceToPriceDto(price);
    }

    @GetMapping("/price")
    public PriceDto getPrice(@RequestParam(name = "date") LocalDateTime date,
                             @RequestParam(name = "product_id") Integer productId,
                             @RequestParam(name = "brand") String brandName) {
        log.info("getPrice method was called");

        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("product_id is not valid");
        }

        Brand brand = brandService.getBrandByName(brandName);
        if (brand == null) {
            throw new NotFoundException("Brand " + brandName + " not found.");
        }

        Price info = pricesService.getPrice(date, brand, productId);
        if (info == null) {
            throw new NotFoundException("Price not found.");
        }
        return mapper.priceToPriceDto(info);
    }

}
