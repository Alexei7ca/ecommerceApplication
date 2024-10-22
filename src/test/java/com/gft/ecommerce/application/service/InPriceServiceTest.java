package com.gft.ecommerce.application.service;

import com.gft.ecommerce.infrastructure.adapter.repository.entity.BrandEntity;
import com.gft.ecommerce.infrastructure.adapter.repository.entity.PriceEntity;
import com.gft.ecommerce.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InPriceServiceTest {

    @Mock
    private PriceRepository pricesRepository;

    @InjectMocks
    private InPriceServiceImpl pricesService;

    private PriceEntity testPriceInfo;
    private List<PriceEntity> priceInfoList;

    @BeforeEach
    public void setUp() {
        testPriceInfo = new PriceEntity();
        priceInfoList = new ArrayList<>();
        priceInfoList.add(testPriceInfo);
    }

    @Test
    public void whenGetAllPrices_thenReturnPricesList() {
        when(pricesRepository.findAll()).thenReturn(priceInfoList);

        List<PriceEntity> found = pricesService.getAllPrices();
        assertThat(found).isEqualTo(priceInfoList);
        verify(pricesRepository).findAll();
    }

    @Test
    public void whenGetPriceById_thenReturnPrice() {
        Integer testId = 1;
        when(pricesRepository.findById(testId)).thenReturn(Optional.of(testPriceInfo));

        PriceEntity found = pricesService.getPriceById(testId);
        assertThat(found).isEqualTo(testPriceInfo);
        verify(pricesRepository).findById(testId);
    }

    @Test
    public void whenGetPrice_thenReturnPrice() {
        LocalDateTime testDate = LocalDateTime.now();
        BrandEntity testBrand = new BrandEntity();
        testBrand.setName("testBrand");
        testBrand.setId(5);
        int testProductId = 1;

        when(pricesRepository.findFirstPriceByProductBrandAndDate(testDate, testBrand, testProductId)).thenReturn(testPriceInfo);

        PriceEntity found = pricesService.getPrice(testDate, testBrand, testProductId);
        assertThat(found).isEqualTo(testPriceInfo);
        verify(pricesRepository).findFirstPriceByProductBrandAndDate(testDate, testBrand, testProductId);
    }

    @Test
    public void whenMultipleThreadsGetPrice_thenReturnConsistentResults() throws InterruptedException {
        LocalDateTime testDate = LocalDateTime.now();
        BrandEntity testBrand = new BrandEntity();
        testBrand.setName("testBrand");
        testBrand.setId(5);
        int testProductId = 1;

        when(pricesRepository.findFirstPriceByProductBrandAndDate(testDate, testBrand, testProductId)).thenReturn(testPriceInfo);

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                PriceEntity found = pricesService.getPrice(testDate, testBrand, testProductId);
                assertThat(found).isEqualTo(testPriceInfo);
            });
        }

        executorService.shutdown();
        boolean finished = executorService.awaitTermination(1, TimeUnit.MINUTES);
        assertTrue(finished, "ExecutorService did not terminate in time");

        verify(pricesRepository, times(numberOfThreads)).findFirstPriceByProductBrandAndDate(testDate, testBrand, testProductId);
    }

}
