package com.gft.ecommerce.infrastructure.adapter.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BRANDS")
public class BrandEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

}
