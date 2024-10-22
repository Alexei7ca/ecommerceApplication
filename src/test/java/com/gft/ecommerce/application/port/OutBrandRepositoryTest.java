package com.gft.ecommerce.application.port;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OutBrandRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BrandRepository brandsRepository;

    private BrandEntity brand;

    @BeforeEach
    public void setUp() {
        brand = new BrandEntity();
        brand.setName("Test Brand");
        brand.setId(5);
    }

    @Test
    public void whenFindByName_thenReturnBrand() {
        entityManager.persist(brand);
        entityManager.flush();

        BrandEntity found = brandsRepository.findByName(brand.getName());

        assertThat(found.getName()).isEqualTo(brand.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        BrandEntity fromDb = brandsRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveBrand_thenBrandIsSaved() {
        BrandEntity savedBrand = brandsRepository.save(brand);

        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getId()).isEqualTo(brand.getId());
        assertThat(savedBrand.getName()).isEqualTo(brand.getName());
    }
}

