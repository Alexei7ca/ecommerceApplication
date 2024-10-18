package com.gft.ecommerce.integration.repository;

import com.gft.ecommerce.domain.Brand;
import com.gft.ecommerce.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void whenSaveBrand_thenBrandIsSaved() {
        Brand savedBrand = brandsRepository.save(brand);

        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getId()).isEqualTo(brand.getId());
        assertThat(savedBrand.getName()).isEqualTo(brand.getName());
    }
}

