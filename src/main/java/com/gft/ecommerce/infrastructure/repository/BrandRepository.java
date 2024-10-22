package com.gft.ecommerce.infrastructure.repository;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    BrandEntity findByName(String name);
}
