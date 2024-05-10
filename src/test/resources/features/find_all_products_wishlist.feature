Feature: A user should find all products from customer wishlist

  Scenario: A customer id null and find all products
    Given a wishlist who's customer id is "null"
    When find products from customer wishlist
    Then should fail for customer id was null

  Scenario: A customer id left blank and find all products
    Given a wishlist who's customer id is left blank
    When find products from customer wishlist
    Then should fail for customer id was left blank

  Scenario Outline: All products are found from customer wishlist
    Given a wishlist who's customer id is "1"
    When find products from customer wishlist
    Then should return product list <listOfProductIdsIdentifier> in customer "1" wishlist
    Examples:
      | listOfProductIdsIdentifier                           |
      | "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20" |

  Scenario: A customer not exists and find all products
    Given a wishlist who's customer id is "2"
    When find products from customer wishlist
    Then should fail for customer id was "2"
