package com.gft.ecommerce.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Price {

    private Integer productId;

    private String brand;

    private Integer priceTariffId;

    private LocalDateTime start;

    private LocalDateTime end;

    private Double price;

    private String currency;
}
