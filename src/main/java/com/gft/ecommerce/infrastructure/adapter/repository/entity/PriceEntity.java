package com.gft.ecommerce.infrastructure.adapter.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private BrandEntity brand;

    @Column(name = "START_DATE")
    private LocalDateTime start;

    @Column(name = "END_DATE")
    private LocalDateTime end;

    @Id
    @Column(name = "PRICE_LIST")
    private Integer priceListId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private String currency;
}

