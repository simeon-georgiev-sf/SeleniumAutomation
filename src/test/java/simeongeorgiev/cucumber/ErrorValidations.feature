@Tag
Feature: Error validation

  Background:
    Given I landed on the ecommerce page

  @ErrorValidation
  Scenario Outline:
    Given I landed on the ecommerce page
    And Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name             | password    |
      | email@emails.com | customPass! |
