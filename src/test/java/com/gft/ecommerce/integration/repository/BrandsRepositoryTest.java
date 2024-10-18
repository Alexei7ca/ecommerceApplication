package com.gft.integration.repository;

import com.decskill.ecommerce.domain.Brand;
import com.decskill.ecommerce.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class BrandsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BrandRepository brandsRepository;

    private Brand brand;

    @BeforeEach
    public void setUp() {
        brand = new Brand();
        brand.setName("Test Brand");
        brand.setId(5);
    }

    @Test
    public void whenFindByName_thenReturnBrand() {
        entityManager.persist(brand);
        entityManager.flush();

        Brand found = brandsRepository.findByName(brand.getName());

        assertThat(found.getName()).isEqualTo(brand.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Brand fromDb = brandsRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }
}

