package com.gft.integration.repository;

import com.decskill.ecommerce.domain.Brand;
import com.decskill.ecommerce.domain.Price;
import com.decskill.ecommerce.repository.PriceRepository;
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
public class PricesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository pricesRepository;

    private Brand brand;

    @BeforeEach
    public void setUp() {
        brand = new Brand();
        brand.setName("Test Brand");
        brand.setId(5);
        entityManager.persistAndFlush(brand);
    }

    @Test
    public void whenValidInput_thenFindPricesByProductBrandAndDateShouldReturnPrices() {
        Price priceInfo = new Price();
        priceInfo.setBrand(brand);
        priceInfo.setPriceListId(10);
        priceInfo.setStart(LocalDateTime.now());
        priceInfo.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo.setProductId(4444);
        priceInfo.setPrice(valueOf("123.45"));
        priceInfo.setCurrency("EUR");

        entityManager.persistAndFlush(priceInfo);


        List<Price> found = pricesRepository.findPricesByProductBrandAndDate(LocalDateTime.now(), brand, priceInfo.getProductId());

        assertThat(found).isNotEmpty();
        assertThat(found.get(0)).isEqualTo(priceInfo);
    }


    @Test
    public void whenValidInput_thenFindFirstPriceByProductBrandAndDateShouldReturnPrice() {
        Price priceInfo = new Price();
        priceInfo.setBrand(brand);
        priceInfo.setPriceListId(10);
        priceInfo.setStart(LocalDateTime.now());
        priceInfo.setEnd(LocalDateTime.now().plusDays(1));
        priceInfo.setProductId(1);
        priceInfo.setPrice(valueOf("123.45"));
        priceInfo.setCurrency("EUR");

        entityManager.persistAndFlush(priceInfo);

        Price found = pricesRepository.findFirstPriceByProductBrandAndDate(LocalDateTime.now(), brand, priceInfo.getProductId());

        assertThat(found).isEqualTo(priceInfo);
    }

}



