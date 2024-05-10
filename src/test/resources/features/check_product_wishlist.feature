Feature: A user should check a product from customer wishlist

  Scenario: A customer id is null and check product
    Given a wishlist who's customer id is "null"
    When check product from customer wishlist
    Then should fail bad request

  Scenario: A customer id left blank and check product
    Given a wishlist who's customer id is left blank
    When check product from customer wishlist
    Then should fail bad request

  Scenario: A product id left blank and check product
    Given a wishlist who's customer id is "1"
    And a product id is "null"
    When check product from customer wishlist
    Then should fail bad request

  Scenario: A product is checked successfully from customer wishlist
    Given a wishlist who's customer id is "1"
    And a product id is "1"
    When check product from customer wishlist
    Then should success status code OK

  Scenario: A customer do not exists and check product
    Given a wishlist who's customer id is "2"
    And a product id is "1"
    When check product from customer wishlist
    Then should fail not found

  Scenario: A product do not exists and check product
    Given a wishlist who's customer id is "1"
    And a product id is "99"
    When check product from customer wishlist
    Then should fail not found
