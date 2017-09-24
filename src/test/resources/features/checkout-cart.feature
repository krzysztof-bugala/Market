Feature: Checkcout cart

  As a customer
  I want to checkout a cart
  So that to get receipt

  Scenario: Checkout cart case standard price
    Given The customer check product with barcode 24732478274 using /market/api/v1/products/24732478274 rest
    When The customer adds 20 products with barcode 24732478274 as second to cart using /market/api/v1/cart/items rest
    And The customer checkouts cart using /market/api/v1/cart/checkout rest
    Then The customer verify that the receipt total price is 800.00

  Scenario: Checkout cart case standard and special price
    Given The customer check product with barcode 24732478274 using /market/api/v1/products/24732478274 rest
    When The customer adds 80 products with barcode 24732478274 as second to cart using /market/api/v1/cart/items rest
    And The customer checkouts cart using /market/api/v1/cart/checkout rest
    Then The customer verify that the receipt total price is 3069.10

  Scenario: Checkout cart case special price
    Given The customer check product with barcode 24732478274 using /market/api/v1/products/24732478274 rest
    When The customer adds 70 products with barcode 24732478274 as second to cart using /market/api/v1/cart/items rest
    And The customer checkouts cart using /market/api/v1/cart/checkout rest
    Then The customer verify that the receipt total price is 2669.10

  Scenario: Checkout cart case two products
    Given The customer check product with barcode 24732478274 using /market/api/v1/products/24732478274 rest
    And The customer check product with barcode 32487984982 using /market/api/v1/products/32487984982 rest
    When The customer adds 80 products with barcode 24732478274 as second to cart using /market/api/v1/cart/items rest
    And The customer adds 20 products with barcode 32487984982 as second to cart using /market/api/v1/cart/items rest
    And The customer checkouts cart using /market/api/v1/cart/checkout rest
    Then The customer verify that the receipt total price is 3248.10

  Scenario: Checkout cart case standard and special price, composite product
    Given The customer check product with barcode 847324782745 using /market/api/v1/products/847324782745 rest
    When The customer adds 80 products with barcode 847324782745 as second to cart using /market/api/v1/cart/items rest
    And The customer checkouts cart using /market/api/v1/cart/checkout rest
    Then The customer verify that the receipt total price is 3720.00


