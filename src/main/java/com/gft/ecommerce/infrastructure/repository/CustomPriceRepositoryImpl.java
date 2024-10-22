package com.gft.ecommerce.infrastructure.repository;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@FieldDefaults(makeFinal = true)
public class CustomPriceRepositoryImpl implements CustomPriceRepository {
    @NonFinal
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PriceEntity findFirstPriceByProductBrandAndDate(LocalDateTime date, BrandEntity brand, int productId) {
        String sql = "SELECT p FROM PriceEntity p WHERE p.brand = :brand AND p.productId = :productId " +
                "AND :date BETWEEN p.start AND p.end ORDER BY p.priority DESC";
        return entityManager.createQuery(sql, PriceEntity.class)
                .setParameter("brand", brand)
                .setParameter("productId", productId)
                .setParameter("date", date)
                .setMaxResults(1)
                .getSingleResult();
    }
}

