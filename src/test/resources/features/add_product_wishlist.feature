Feature: A user should be able to add product to customer wishlist

  Scenario: A customer id is null and add new product
    Given a wishlist who's customer id is "null"
    And a product id is "null" in add product request
    When add product to customer wishlist
    Then should fail for customer id was null

  Scenario: A product id is null and add new product
    Given a wishlist who's customer id is "1"
    And a product id is "null" in add product request
    When add product to customer wishlist
    Then should fail for product id was null

  Scenario: A customer id left blank and add new product
    Given a wishlist who's customer id is left blank
    And a product id is "1" in add product request
    When add product to customer wishlist
    Then should fail for customer id was left blank

  Scenario: A product added successfully into customer wishlist
    Given a wishlist who's customer id is "1"
    And a product id is "1" in add product request
    When add product to customer wishlist
    Then should success for add product into customer wishlist

  Scenario: A product duplicated into customer wishlist
    Given a wishlist who's customer id is "1"
    And a product id is "1" in add product request
    When add product to customer wishlist
    Then should fail for existing product id "1" in wishlist

  Scenario Outline: Add 20 products into customer wishlist
    Given a wishlist who's customer id is "1"
    And a wishlist with a list product id is <listOfProductIdsIdentifier>
    When add product list to customer wishlist
    Then should success for add product into customer wishlist
    Examples:
      | listOfProductIdsIdentifier |
      | "2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20" |

  Scenario: A product fail to add into a fully customer wishlist
    Given a wishlist who's customer id is "1"
    And a product id is "21" in add product request
    When add product to customer wishlist
    Then should fail add product "21" when limit of 20 products into wishlist