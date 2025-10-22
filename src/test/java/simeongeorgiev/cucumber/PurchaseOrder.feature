@Tag
Feature: Purchase an order from the eCommerce Website
	

  Background:
  Given I landed on the ecommerce page
	
	
  @Regression
  Scenario Outline: Positive test for placing and order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And  Checkout and verify <productName> and submit order
    Then "Thankyou for the order." is displayed on the confirmation page

    Examples:
      | name              | password     | productName     |
      | email@emails.com  | customPass!1 | ADIDAS ORIGINAL |
