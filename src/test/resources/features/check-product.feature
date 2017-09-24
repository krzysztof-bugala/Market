Feature: Check product

  As a customer
  I want to check a product using barcode
  So that to see the product information

  Scenario: Check product information
    Given The product with barcode 24732478274
    When The product is checked using /market/api/v1/products/24732478274 rest
    Then The information about the product is returned

