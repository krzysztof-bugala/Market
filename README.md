Market
======

 

Description
-----------

Market is then name of checkout component which is responsible for checkout process.
The customer may add products to cart and checkout it to receive the receipt.
- listing all products in store
- displaying information about specific product
- adding product to store cart
- removing product from store cart
- displaying products which are in cart
- displaying current price of the products in cart
- checking  out the product in cart. This action generate a receipt.



Scenarios
---------

### Listing the products or product

A customer is able to see the list of products.

### Show information about the product.

The cusotmer may see the information about specific product.

### Adding product to cart

The customer may add a product to the cart. The chosen quantity of product may be added to cart.

### Removing product from cart

The customer may remove the specific product from cart

### Listing the cart items

The customer may see the cart items which are in cart. 
The cart item has information about the product and product quantity.

### Checking the total price of the cart

The customer may check the total price of the cart.

### Checking out 

The checking out returns the receipt with bought products and total price. It removes all producs from cart. 


Technologies used in project
----------------------------

-   Spring boot

-   H2 memory database

-   Junit

-   Mockito

-   JPA

-   Java 8

- Cucumber

 

Installation and running
------------------------

Enter to folder Market and run command following command.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
mvn spring-boot:run
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The above command will build and run application.

The web API may be tested on browser by using following address

### http://localhost:8080/market/api/v1/

 

Default configuration
---------------------

There are two type of products.
- standard product has standard price(price for one product) and also special price(if the user buy the specific quantity of product)
- composite product contains two products, 
the price of composite product is cheaper than buying products alone. So if the user buys composite
product which contains product A and B in this case will buy products together for cheaper price. The composite product has also the 
standard and special price.

The products may be found in table standard_product.


Database
--------

Database console http://localhost:8080/h2

JDBC URL - jdbc:h2:mem:market

create.sql - this sql script creates the default database configuration

 

Twiter web API
--------------

 

### GET market/api/v1/products

Returns information about products in store

Example request  
GET /market/api/v1/products

### GET market/api/v1/products/{barcode}

Returns information about product which has barcode

Errors
- Product doesn't exists

Example request  
GET /market/api/v1/products/24732478274

 
### GET /market/api/v1/cart/items

Returns the cart items in cart. Cart item contains the product and product quantity.

Example request  
GET /market/api/v1/cart/items

 
### POST /market/api/v1/cart/items

The product in specific quantity is added to cart.

Errors
- Product doesn't exists

Following request removes 41 products from cart.
POST /market/api/v1/cart/items

{
    "productBarcode": 847324782746,
    "quantity": 41
}

###  DELETE  /market/api/v1/cart/items/{barcode}

All the product with barcode will be removed from cart. 
If in the cart there are 5 products with barcode they will be removed.

DELETE /market/api/v1/cart/items/847324782746

 
### GET /market/api/v1/cart/totalPrice

Returns the total price of products in cart.

Example request

GET /market/api/v1/cart/totalPrice


### POST /market/api/v1/cart/checkout

Checkout the cart and returns the receipt with total price of bought products.
The products are removed from cart.

Example request

POST /market/api/v1/cart/checkout


 

 
