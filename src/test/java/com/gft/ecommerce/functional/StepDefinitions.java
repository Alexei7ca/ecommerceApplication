package com.gft.ecommerce.functional;

import com.gft.ecommerce.dto.BrandDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

public class StepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;
    private ResponseEntity<BrandDto> brandResponse;
    private String brandName;

    @Given("the application is running")
    public void the_application_is_running() {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Application is not running!");
    }

    // Scenario 1: Add a new brand
    @Given("the brand {string} does not exist")
    public void the_brand_does_not_exist(String brandName) {
        this.brandName = brandName;
        ResponseEntity<String> response = restTemplate.getForEntity("/api/brands", String.class);
        Assertions.assertFalse(response.getBody().contains(brandName), "Brand already exists: " + brandName);
    }

    @When("I create a new brand with name {string}")
    public void i_create_a_new_brand_with_name(String brandName) {
        String requestBody = String.format("{\"name\": \"%s\"}", brandName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        response = restTemplate.postForEntity("/api/brands", entity, String.class);
    }

    @Then("the brand {string} should be created successfully")
    public void the_brand_should_be_created_successfully(String brandName) {
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Brand creation failed");
    }

    @Then("I should see the brand {string} in the list of brands")
    public void i_should_see_the_brand_in_the_list_of_brands(String brandName) {
        response = restTemplate.getForEntity("/api/brands", String.class);
        Assertions.assertTrue(response.getBody().contains(brandName), "Brand not found in list: " + brandName);
    }

    // Scenario 2: Add a duplicate brand
    @Given("the brand {string} exists")
    public void the_brand_exists(String brandName) {
        this.brandName = brandName;
        ResponseEntity<String> response = restTemplate.getForEntity("/api/brands", String.class);
        if (!response.getBody().contains(brandName)) {
            // Create the brand if it doesn't exist
            i_create_a_new_brand_with_name(brandName);
            the_brand_should_be_created_successfully(brandName);
        }
    }

    @When("I try to create a new brand with name {string}")
    public void i_try_to_create_a_new_brand_with_name(String brandName) {
        String requestBody = String.format("{\"name\": \"%s\"}", brandName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        response = restTemplate.postForEntity("/api/brands", entity, String.class);
    }

    @Then("I should receive a conflict error message")
    public void i_should_receive_a_conflict_error_message() {
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode(), "Expected conflict error, but got: " + response.getStatusCode());
    }

    // Scenario 3: Get all brands
    @Given("the following brands exist:")
    public void the_following_brands_exist(List<String> expectedBrands) {
        for (String brandName : expectedBrands) {
            // Check if the brand already exists
            ResponseEntity<BrandDto[]> response = restTemplate.getForEntity("/api/brands", BrandDto[].class);
            List<BrandDto> brands = List.of(response.getBody());

            // If the brand doesn't exist, create it
            boolean brandExists = brands.stream().anyMatch(brand -> brand.getName().equals(brandName));
            if (!brandExists) {
                String requestBody = String.format("{\"name\": \"%s\"}", brandName);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
                restTemplate.postForEntity("/api/brands", entity, String.class);
            }
        }
    }


    @When("I request the list of all brands")
    public void i_request_the_list_of_all_brands() {
        response = restTemplate.getForEntity("/api/brands", String.class);
    }

    @Then("I should see the following brands:")
    public void i_should_see_the_following_brands(List<String> expectedBrands) {
        // Fetch the list of brands from the server
        ResponseEntity<BrandDto[]> response = restTemplate.getForEntity("/api/brands", BrandDto[].class);
        List<BrandDto> actualBrands = List.of(response.getBody());

        for (String expectedBrand : expectedBrands) {
            boolean brandExists = actualBrands.stream().anyMatch(brand -> brand.getName().equals(expectedBrand));
            Assertions.assertTrue(brandExists, "Brand not found in list: " + expectedBrand);
        }
    }


    // Scenario 4: Get a single brand by name
    @When("I request the brand details for {string}")
    public void i_request_the_brand_details_for(String brandName) {
        brandResponse = restTemplate.getForEntity("/api/brands/" + brandName, BrandDto.class);

        System.out.println("Brand response: " + brandResponse.getBody());
    }


    @Then("the response should include the following brand details:")
    public void the_response_should_include_the_following_brand_details(Map<String, String> expectedDetails) {
        BrandDto actualBrand = brandResponse.getBody();

        Assertions.assertNotNull(actualBrand, "The response body is null");

        for (Map.Entry<String, String> entry : expectedDetails.entrySet()) {
            String expectedKey = entry.getKey();
            String expectedValue = entry.getValue();

            switch (expectedKey.toLowerCase()) {
                case "name":
                    Assertions.assertEquals(expectedValue, actualBrand.getName(), "Brand name does not match");
                    break;
                case "id":
                    Assertions.assertEquals(Integer.parseInt(expectedValue), actualBrand.getId(), "Brand ID does not match");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + expectedKey);
            }
        }
    }

}


