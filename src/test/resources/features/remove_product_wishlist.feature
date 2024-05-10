Feature: A user should remove a product from customer wishlist

  Scenario: A customer id is null and remove product
    Given a wishlist who's customer id is "null"
    When remove product from customer wishlist
    Then should fail for customer id was null

  Scenario: A customer id left blank and remove product
    Given a wishlist who's customer id is left blank
    When remove product from customer wishlist
    Then should fail for customer id was left blank

  Scenario: A product id is null and remove product
    Given a wishlist who's customer id is "1"
    And a product id is "null"
    When remove product from customer wishlist
    Then should fail for product id was null

  Scenario: A product is removed successfully from customer wishlist
    Given a wishlist who's customer id is "1"
    And a product id is "1"
    When remove product from customer wishlist
    Then should success status code OK

  Scenario: A customer do not exists and remove product
    Given a wishlist who's customer id is "2"
    And a product id is "1"
    When remove product from customer wishlist
    Then should fail for customer id was "2"

  Scenario: A product do not exists and remove product
    Given a wishlist who's customer id is "1"
    And a product id is "99"
    When remove product from customer wishlist
    Then should fail for product id was "99" from customer "1" wishlist
