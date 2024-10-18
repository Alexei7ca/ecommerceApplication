Feature: Brand Management
  This feature tests the management of brands within the eCommerce system, including creating new brands and retrieving a list of brands.

  Background:
    Given the application is running

  Scenario: Add a new brand
    Given the brand "Nike" does not exist
    When I create a new brand with name "Nike"
    Then the brand "Nike" should be created successfully
    And I should see the brand "Nike" in the list of brands

  Scenario: Add a duplicate brand
    Given the brand "Nike" exists
    When I try to create a new brand with name "Nike"
    Then I should receive a conflict error message

  Scenario: Get all brands
    Given the following brands exist:
      | ZARA |
      | CROCS   |
    When I request the list of all brands
    Then I should see the following brands:
      | ZARA |
      | CROCS   |

  Scenario: Get a single brand by name
    Given the brand "Adidas" exists
    When I request the brand details for "Adidas"
    Then the response should include the following brand details:
      | name   | Adidas |
