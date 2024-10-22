package com.gft.ecommerce.infrastructure.repository;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer>, CustomPriceRepository {

    @Query(value = "SELECT p FROM PriceEntity p where p.brand=:brand and p.productId=:productId and :date between p.start and p.end")
    List<PriceEntity> findPricesByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId);

}

