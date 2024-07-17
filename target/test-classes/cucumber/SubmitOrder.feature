
@tag
Feature: Purchasing the order from ecommerce website
  I want to use this template for my feature file

  Background:
  i landed on the Ecommerce Page

  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given login with useremail <username> password <password>
    When add the  product <productName> to Cart.
    And checkout the <productName> and submit the order
  
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name                        | value        | Product    |
      | selenium.practice@gmail.com | Manibabu@373 | ZARA COAT 3|
  
