package com.gft.ecommerce.application.port;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Double.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class OutPriceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository pricesRepository;

    private BrandEntity brand;

    @BeforeEach
    public void setUp() {
        brand = new BrandEntity();
        brand.setName("Test Brand");
        brand.setId(5);
        entityManager.persistAndFlush(brand);
    }

    @Test
    public void whenValidInput_thenFindPricesByProductBrandAndDateShouldReturnPrices() {
        PriceEntity priceInfo = new PriceEntity();
        priceInfo.setBrand(brand);
        priceInfo.setPriceListId(10);
        priceInfo.setStart(LocalDateTime.now());
        priceInfo.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo.setProductId(4444);
        priceInfo.setPrice(valueOf("123.45"));
        priceInfo.setCurrency("EUR");

        entityManager.persistAndFlush(priceInfo);


        List<PriceEntity> found = pricesRepository.findPricesByProductBrandAndDate(LocalDateTime.now(), brand, priceInfo.getProductId());

        assertThat(found).isNotEmpty();
        assertThat(found.get(0)).isEqualTo(priceInfo);
    }


    @Test
    public void whenValidInput_thenFindFirstPriceByProductBrandAndDateShouldReturnPrice() {
        PriceEntity priceInfo = new PriceEntity();
        priceInfo.setBrand(brand);
        priceInfo.setPriceListId(10);
        priceInfo.setStart(LocalDateTime.now());
        priceInfo.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo.setProductId(1);
        priceInfo.setPrice(valueOf("123.45"));
        priceInfo.setCurrency("EUR");

        entityManager.persistAndFlush(priceInfo);

        PriceEntity found = pricesRepository.findFirstPriceByProductBrandAndDate(LocalDateTime.now(), brand, priceInfo.getProductId());

        assertThat(found).isEqualTo(priceInfo);
    }

}



