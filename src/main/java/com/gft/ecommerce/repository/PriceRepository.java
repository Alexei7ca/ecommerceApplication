package com.gft.ecommerce.repository;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer>, CustomPriceRepository {

    @Query(value = "SELECT p FROM Price p where p.brand=:brand and p.productId=:productId and :date between p.start and p.end")
    List<Price> findPricesByProductBrandAndDate(LocalDateTime date, Brand brand, int productId);

}

